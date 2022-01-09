package me.star.system.dto;

import lombok.Data;
import me.star.annotation.DataPermission;
import me.star.annotation.Query;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Star Chou
 * @description /
 * @create 2022/1/9
 */
@Data
@DataPermission(fieldName = "id")
public class DeptQueryCriteria{

    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    @Query
    private Boolean enabled;

    @Query
    private Long pid;

    @Query(type = Query.Type.IS_NULL, propName = "pid")
    private Boolean pidIsNull;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
