package me.star.repository;

import me.star.domain.vo.GenConfig;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author sida_zhou
 * @description
 * @date 2021/12/29
 */
public interface GenConfigRepository extends JpaRepository<GenConfig, Long> {

    /**
     * 查询表配置
     *
     * @param tableName 表名
     * @return /
     */
    GenConfig findByTableName(String tableName);
}
