package me.star.system.dto;

import lombok.Data;
import me.star.annotation.Query;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Star Chou
 * @description /
 * @create 2022/1/8 20:30
 */
@Data
public class RoleQueryCriteria {

    @Query(blurry = "name,description")
    private String blurry;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
