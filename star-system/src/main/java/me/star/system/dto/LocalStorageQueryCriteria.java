package me.star.system.dto;

import lombok.Data;
import me.star.annotation.Query;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Star Chou
 * @description /
 * @create 2022/1/15
 */
@Data
public class LocalStorageQueryCriteria {

    @Query(blurry = "name,suffix,type,createBy,size")
    private String blurry;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
