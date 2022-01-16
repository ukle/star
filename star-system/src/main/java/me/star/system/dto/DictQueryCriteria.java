package me.star.system.dto;

import lombok.Data;
import me.star.annotation.Query;

/**
 * @author Star Chou
 * @description /
 * @create 2022/1/14
 */
@Data
public class DictQueryCriteria {

    @Query(blurry = "name,description")
    private String blurry;
}

