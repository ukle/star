package me.star.system.dto;

import lombok.Data;
import me.star.base.BaseDTO;

import java.io.Serializable;
import java.util.List;

/**
 * @author Star Chou
 * @description /
 * @create 2022/1/14
 */
@Data
public class DictDto extends BaseDTO implements Serializable {

    private Long id;

    private List<DictDetailDto> dictDetails;

    private String name;

    private String description;
}
