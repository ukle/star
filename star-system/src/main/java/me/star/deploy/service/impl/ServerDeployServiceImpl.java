package me.star.deploy.service.impl;

import lombok.RequiredArgsConstructor;
import me.star.deploy.domain.dto.ServerDeployDto;
import me.star.deploy.domain.entity.ServerDeploy;
import me.star.deploy.repository.ServerDeployRepository;
import me.star.deploy.service.ServerDeployService;
import me.star.deploy.service.dto.ServerDeployQueryCriteria;
import me.star.deploy.service.mapstruct.ServerDeployMapper;
import me.star.deploy.utils.ExecuteShellUtil;
import me.star.utils.PageUtil;
import me.star.utils.QueryHelp;
import me.star.utils.ValidationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * @author sida_zhou
 * @description
 * @date 2022/1/7
 */
@Service
@RequiredArgsConstructor
public class ServerDeployServiceImpl implements ServerDeployService {

    private final ServerDeployRepository serverDeployRepository;
    private final ServerDeployMapper serverDeployMapper;

    @Override
    public Object queryAll(ServerDeployQueryCriteria criteria, Pageable pageable) {
        Page<ServerDeploy> page = serverDeployRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(serverDeployMapper::toDto));
    }

    @Override
    public List<ServerDeployDto> queryAll(ServerDeployQueryCriteria criteria) {
        return serverDeployMapper.toDto(serverDeployRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    public ServerDeployDto findById(Long id) {
        ServerDeploy server = serverDeployRepository.findById(id).orElseGet(ServerDeploy::new);
        ValidationUtil.isNull(server.getId(), "ServerDeploy", "id", id);
        return serverDeployMapper.toDto(server);
    }

    @Override
    public ServerDeployDto findByIp(String ip) {
        ServerDeploy deploy = serverDeployRepository.findByIp(ip);
        return serverDeployMapper.toDto(deploy);
    }

    @Override
    public Boolean testConnect(ServerDeploy resources) {
        ExecuteShellUtil executeShellUtil = null;
        try {
            executeShellUtil = new ExecuteShellUtil(resources.getIp(), resources.getAccount(), resources.getPassword(), resources.getPort());
            return executeShellUtil.execute("ls") == 0;
        } catch (Exception e) {
            return false;
        } finally {
            if (executeShellUtil != null) {
                executeShellUtil.close();
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(ServerDeploy resources) {
        serverDeployRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ServerDeploy resources) {
        ServerDeploy serverDeploy = serverDeployRepository.findById(resources.getId()).orElseGet(ServerDeploy::new);
        ValidationUtil.isNull(serverDeploy.getId(), "ServerDeploy", "id", resources.getId());
        serverDeploy.copy(resources);
        serverDeployRepository.save(serverDeploy);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Set<Long> ids) {
        for (Long id : ids) {
            serverDeployRepository.deleteById(id);
        }
    }

}
