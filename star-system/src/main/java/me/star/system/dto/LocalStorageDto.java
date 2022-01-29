package me.star.system.dto;

import lombok.Data;
import me.star.base.BaseDTO;

import java.io.Serializable;

/**
 * @author Star Chou
 * @description /
 * @create 2022/1/15
 */
@Data
public class LocalStorageDto extends BaseDTO implements Serializable {

    private Long id;

    private String realName;

    private String name;

    private String suffix;

    private String type;

    private String size;
}
