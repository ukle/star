package me.star.deploy.service.dto;

import lombok.Data;
import me.star.annotation.Query;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author sida_zhou
 * @description
 * @date 2022/1/7
 */
@Data
public class ServerDeployQueryCriteria {

    /**
     * 模糊
     */
    @Query(blurry = "name,ip,account")
    private String blurry;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
