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
public class DeployHistoryQueryCriteria{

    /**
     * 精确
     */
    @Query(blurry = "appName,ip,deployUser")
    private String blurry;

    @Query
    private Long deployId;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> deployDate;
}
