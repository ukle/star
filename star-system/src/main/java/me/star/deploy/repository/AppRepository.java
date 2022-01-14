package me.star.deploy.repository;

import me.star.deploy.domain.entity.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author sida_zhou
 * @description
 * @date 2022/1/14
 */
public interface AppRepository extends JpaRepository<App, Long>, JpaSpecificationExecutor<App> {
}
