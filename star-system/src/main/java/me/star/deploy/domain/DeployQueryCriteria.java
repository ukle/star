package me.star.deploy.domain;

import lombok.Data;
import me.star.annotation.Query;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author sida_zhou
 * @description
 * @date 2022/1/4
 */
@Data
public class DeployQueryCriteria {

    /**
     * 模糊
     */
    @Query(type = Query.Type.INNER_LIKE, propName = "name", joinName = "app")
    private String appName;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
