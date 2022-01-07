package me.star.deploy.repository;

import me.star.deploy.domain.entity.ServerDeploy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author sida_zhou
 * @description
 * @date 2022/1/7
 */
public interface ServerDeployRepository extends JpaRepository<ServerDeploy, Long>, JpaSpecificationExecutor<ServerDeploy> {

    /**
     * 根据IP查询
     * @param ip /
     * @return /
     */
    ServerDeploy findByIp(String ip);
}
