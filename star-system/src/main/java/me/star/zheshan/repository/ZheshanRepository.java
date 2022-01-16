package me.star.zheshan.repository;

import me.star.zheshan.domain.Zheshan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author Star Chou
* @date 2022-01-15
**/
public interface ZheshanRepository extends JpaRepository<Zheshan, Long>, JpaSpecificationExecutor<Zheshan> {
}
