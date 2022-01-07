package me.star.deploy.repository;

import me.star.deploy.domain.entity.DeployHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author sida_zhou
 * @description
 * @date 2022/1/7
 */
public interface DeployHistoryRepository extends JpaRepository<DeployHistory, String>, JpaSpecificationExecutor<DeployHistory> {
}

