package me.star.system.dto;

import lombok.Data;
import me.star.annotation.Query;

/**
 * @author Star Chou
 * @description /
 * @create 2022/1/14
 */
@Data
public class DictDetailQueryCriteria {

    @Query(type = Query.Type.INNER_LIKE)
    private String label;

    @Query(propName = "name",joinName = "dict")
    private String dictName;
}
