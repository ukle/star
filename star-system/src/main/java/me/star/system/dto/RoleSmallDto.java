package me.star.system.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Star Chou
 * @description /
 * @create 2022/1/8 19:48
 */
@Data
public class RoleSmallDto implements Serializable {

    private Long id;

    private String name;

    private Integer level;

    private String dataScope;
}
