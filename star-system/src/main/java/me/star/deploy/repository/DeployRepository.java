package me.star.deploy.repository;

import me.star.deploy.domain.entity.Deploy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author sida_zhou
 * @description
 * @date 2022/1/5
 */
public interface DeployRepository extends JpaRepository<Deploy, Long>, JpaSpecificationExecutor<Deploy> {
}

