package me.star.zheshan.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.util.List;
import me.star.annotation.Query;

/**
* @author Star Chou
* @date 2022-01-15
**/
@Data
public class ZheshanQueryCriteria{

    /** 精确 */
    @Query
    private String zsType;
    /** BETWEEN */
    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
