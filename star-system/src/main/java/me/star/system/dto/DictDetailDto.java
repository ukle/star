package me.star.system.dto;

import lombok.Data;
import me.star.base.BaseDTO;

import java.io.Serializable;

/**
 * @author Star Chou
 * @description /
 * @create 2022/1/14
 */
@Data
public class DictDetailDto extends BaseDTO implements Serializable {

    private Long id;

    private DictSmallDto dict;

    private String label;

    private String value;

    private Integer dictSort;
}
