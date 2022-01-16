package me.star.system.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.star.annotation.Query;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Star Chou
 * @description /
 * @create 2022/1/14
 */
@Data
@NoArgsConstructor
public class JobQueryCriteria {

    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    @Query
    private Boolean enabled;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
