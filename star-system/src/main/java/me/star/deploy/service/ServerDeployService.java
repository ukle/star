package me.star.deploy.service;

import me.star.deploy.domain.dto.ServerDeployDto;
import me.star.deploy.domain.entity.ServerDeploy;
import me.star.deploy.service.dto.ServerDeployQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @author sida_zhou
 * @description
 * @date 2022/1/7
 */
public interface ServerDeployService {

    /**
     * 分页查询
     * @param criteria 条件
     * @param pageable 分页参数
     * @return /
     */
    Object queryAll(ServerDeployQueryCriteria criteria, Pageable pageable);

    /**
     * 查询全部数据
     * @param criteria 条件
     * @return /
     */
    List<ServerDeployDto> queryAll(ServerDeployQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id /
     * @return /
     */
    ServerDeployDto findById(Long id);

    /**
     * 创建
     * @param resources /
     */
    void create(ServerDeploy resources);

    /**
     * 编辑
     * @param resources /
     */
    void update(ServerDeploy resources);

    /**
     * 删除
     * @param ids /
     */
    void delete(Set<Long> ids);

    /**
     * 根据IP查询
     * @param ip /
     * @return /
     */
    ServerDeployDto findByIp(String ip);

    /**
     * 测试登录服务器
     * @param resources /
     * @return /
     */
    Boolean testConnect(ServerDeploy resources);

}
