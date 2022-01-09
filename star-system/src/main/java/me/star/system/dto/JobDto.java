package me.star.system.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.star.base.BaseDTO;

import java.io.Serializable;

/**
 * @author Star Chou
 * @description /
 * @create 2022/1/9
 */
@Data
@NoArgsConstructor
public class JobDto extends BaseDTO implements Serializable {

    private Long id;

    private Integer jobSort;

    private String name;

    private Boolean enabled;

    public JobDto(String name, Boolean enabled) {
        this.name = name;
        this.enabled = enabled;
    }
}
