package me.star.repository;

import me.star.domain.vo.ColumnInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author sida_zhou
 * @description
 * @date 2021/12/29
 */
public interface ColumnInfoRepository extends JpaRepository<ColumnInfo, Long> {

    /**
     * 查询表信息
     * @param tableName 表格名
     * @return 表信息
     */
    List<ColumnInfo> findByTableNameOrderByIdAsc(String tableName);
}
