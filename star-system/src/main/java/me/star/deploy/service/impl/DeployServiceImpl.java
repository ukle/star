package me.star.deploy.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.star.deploy.domain.DeployQueryCriteria;
import me.star.deploy.domain.dto.AppDto;
import me.star.deploy.domain.dto.DeployDto;
import me.star.deploy.domain.dto.ServerDeployDto;
import me.star.deploy.domain.entity.Deploy;
import me.star.deploy.domain.entity.DeployHistory;
import me.star.deploy.repository.DeployRepository;
import me.star.deploy.service.DeployHistoryService;
import me.star.deploy.service.DeployService;
import me.star.deploy.service.ServerDeployService;
import me.star.deploy.service.mapstruct.DeployMapper;
import me.star.deploy.utils.ExecuteShellUtil;
import me.star.deploy.utils.ScpClientUtil;
import me.star.exception.BadRequestException;
import me.star.utils.PageUtil;
import me.star.utils.QueryHelp;
import me.star.utils.SecurityUtils;
import me.star.utils.ValidationUtil;
import me.star.websocket.MsgType;
import me.star.websocket.SocketMsg;
import me.star.websocket.WebSocketServer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author sida_zhou
 * @description
 * @date 2022/1/5
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DeployServiceImpl implements DeployService {

    private final String FILE_SEPARATOR = "/";
    private final DeployRepository deployRepository;
    private final DeployMapper deployMapper;
    private final ServerDeployService serverDeployService;
    private final DeployHistoryService deployHistoryService;

    /**
     * 循环次数
     */
    private final Integer count = 30;

    @Override
    public Object queryAll(DeployQueryCriteria criteria, Pageable pageable) {
        Page<Deploy> page = deployRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(deployMapper::toDto));
    }

    @Override
    public List<DeployDto> queryAll(DeployQueryCriteria criteria) {
        return deployMapper.toDto(deployRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    public DeployDto findById(Long id) {
        Deploy deploy = deployRepository.findById(id).orElseGet(Deploy::new);
        ValidationUtil.isNull(deploy.getId(), "Deploy", "id", id);
        return deployMapper.toDto(deploy);
    }

    @Override
    public void create(Deploy resources) {
        deployRepository.save(resources);
    }

    @Override
    public void update(Deploy resources) {
        Deploy deploy = deployRepository.findById(resources.getId()).orElseGet(Deploy::new);
        ValidationUtil.isNull(deploy.getId(), "Deploy", "id", resources.getId());
        deploy.copy(resources);
        deployRepository.save(deploy);
    }

    @Override
    public void delete(Set<Long> ids) {
        for (Long id : ids) {
            deployRepository.deleteById(id);
        }
    }

    @Override
    public void deploy(String fileSavePath, Long appId) {
        deployApp(fileSavePath, appId);
    }

    private void sendMsg(String msg, MsgType msgType) {
        WebSocketServer.sendInfo(new SocketMsg(msg, msgType), "deploy");
    }

    /**
     * @param fileSavePath 本机路径
     * @param id           ID
     */
    private void deployApp(String fileSavePath, Long id) {
        DeployDto deploy = findById(id);
        if (deploy == null) {
            sendMsg("部署信息不存在", MsgType.ERROR);
            throw new BadRequestException("部署信息不存在");
        }
        AppDto app = deploy.getApp();
        if (app == null) {
            sendMsg("包对应应用信息不存在", MsgType.ERROR);
            throw new BadRequestException("包对应应用信息不存在");
        }
        int port = app.getPort();
        //这个是服务器部署路径
        String uploadPath = app.getUploadPath();
        StringBuilder sb = new StringBuilder();
        String msg;
        Set<ServerDeployDto> deploys = deploy.getDeploys();
        for (ServerDeployDto deployDTO : deploys) {
            String ip = deployDTO.getIp();
            ExecuteShellUtil executeShellUtil = getExecuteShellUtil(ip);
            //判断是否第一次部署
            boolean flag = checkFile(executeShellUtil, app);
            //第一步要确认服务器上有这个目录
            executeShellUtil.execute("mkdir -p " + app.getUploadPath());
            executeShellUtil.execute("mkdir -p " + app.getBackupPath());
            executeShellUtil.execute("mkdir -p " + app.getDeployPath());
            //上传文件
            msg = String.format("登陆到服务器:%s", ip);
            ScpClientUtil scpClientUtil = getScpClientUtil(ip);
            log.info(msg);
            sendMsg(msg, MsgType.INFO);
            msg = String.format("上传文件到服务器:%s<br>目录:%s下，请稍等...", ip, uploadPath);
            sendMsg(msg, MsgType.INFO);
            scpClientUtil.putFile(fileSavePath, uploadPath);
            if (flag) {
                sendMsg("停止原来应用", MsgType.INFO);
                //停止应用
                stopApp(port, executeShellUtil);
                sendMsg("备份原来应用", MsgType.INFO);
                //备份应用
                backupApp(executeShellUtil, ip, app.getDeployPath() + FILE_SEPARATOR, app.getName(), app.getBackupPath() + FILE_SEPARATOR, id);
            }
            sendMsg("部署应用", MsgType.INFO);
            //部署文件,并启动应用
            String deployScript = app.getDeployScript();
            executeShellUtil.execute(deployScript);
            sleep(3);
            sendMsg("应用部署中，请耐心等待部署结果，或者稍后手动查看部署状态", MsgType.INFO);
            int i = 0;
            boolean result = false;
            // 由于启动应用需要时间，所以需要循环获取状态，如果超过30次，则认为是启动失败
            while (i++ < count) {
                result = checkIsRunningStatus(port, executeShellUtil);
                if (result) {
                    break;
                }
                // 休眠6秒
                sleep(6);
            }
            sb.append("服务器:").append(deployDTO.getName()).append("<br>应用:").append(app.getName());
            sendResultMsg(result, sb);
            executeShellUtil.close();
        }
    }

    private void sendResultMsg(boolean result, StringBuilder sb) {
        if (result) {
            sb.append("<br>启动成功!");
            sendMsg(sb.toString(), MsgType.INFO);
        } else {
            sb.append("<br>启动失败!");
            sendMsg(sb.toString(), MsgType.ERROR);
        }
    }

    /**
     * 指定端口程序是否在运行
     *
     * @param port             端口
     * @param executeShellUtil /
     * @return true 正在运行  false 已经停止
     */
    private boolean checkIsRunningStatus(int port, ExecuteShellUtil executeShellUtil) {
        String result = executeShellUtil.executeForResult(String.format("fuser -n tcp %d", port));
        return result.indexOf("/tcp:") > 0;
    }

    private void sleep(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }

    private void backupApp(ExecuteShellUtil executeShellUtil, String ip, String fileSavePath, String appName, String backupPath, Long id) {
        String deployDate = DateUtil.format(new Date(), DatePattern.PURE_DATETIME_PATTERN);
        StringBuilder sb = new StringBuilder();
        backupPath += appName + FILE_SEPARATOR + deployDate + "\n";
        sb.append("mkdir -p ").append(backupPath);
        sb.append("mv -f ").append(fileSavePath);
        sb.append(appName).append(" ").append(backupPath);
        log.info("备份应用脚本:" + sb.toString());
        executeShellUtil.execute(sb.toString());
        //还原信息入库
        DeployHistory deployHistory = new DeployHistory();
        deployHistory.setAppName(appName);
        deployHistory.setDeployUser(SecurityUtils.getCurrentUsername());
        deployHistory.setIp(ip);
        deployHistory.setDeployId(id);
        deployHistoryService.create(deployHistory);
    }

    private ExecuteShellUtil getExecuteShellUtil(String ip) {
        ServerDeployDto serverDeployDTO = serverDeployService.findByIp(ip);
        if (serverDeployDTO == null) {
            sendMsg("IP对应服务器信息不存在：" + ip, MsgType.ERROR);
            throw new BadRequestException("IP对应服务器信息不存在：" + ip);
        }
        return new ExecuteShellUtil(ip, serverDeployDTO.getAccount(), serverDeployDTO.getPassword(), serverDeployDTO.getPort());
    }

    private boolean checkFile(ExecuteShellUtil executeShellUtil, AppDto appDTO) {
        String result = executeShellUtil.executeForResult("find " + appDTO.getDeployPath() + " -name " + appDTO.getName());
        return result.indexOf(appDTO.getName()) > 0;
    }

    private ScpClientUtil getScpClientUtil(String ip) {
        ServerDeployDto serverDeployDTO = serverDeployService.findByIp(ip);
        if (serverDeployDTO == null) {
            sendMsg("IP对应服务器信息不存在：" + ip, MsgType.ERROR);
            throw new BadRequestException("IP对应服务器信息不存在：" + ip);
        }
        return ScpClientUtil.getInstance(ip, serverDeployDTO.getPort(), serverDeployDTO.getAccount(), serverDeployDTO.getPassword());
    }

    /**
     * 停App
     *
     * @param port             端口
     * @param executeShellUtil /
     */
    private void stopApp(int port, ExecuteShellUtil executeShellUtil) {
        //发送停止命令
        executeShellUtil.execute(String.format("lsof -i :%d|grep -v \"PID\"|awk '{print \"kill -9\",$2}'|sh", port));
    }

    @Override
    public String serverStatus(Deploy resources) {
        return null;
    }

    @Override
    public String startServer(Deploy resources) {
        return null;
    }

    @Override
    public String stopServer(Deploy resources) {
        return null;
    }

    @Override
    public String serverReduction(DeployHistory resources) {
        return null;
    }

    @Override
    public void download(List<DeployDto> queryAll, HttpServletResponse response) throws IOException {

    }
}
