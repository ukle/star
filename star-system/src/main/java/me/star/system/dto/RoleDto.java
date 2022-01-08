package me.star.system.dto;

import lombok.Data;
import me.star.base.BaseDTO;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * @author Star Chou
 * @description /
 * @create 2022/1/8 20:28
 */
@Data
public class RoleDto extends BaseDTO implements Serializable {

    private Long id;

    private Set<MenuDto> menus;

    private Set<DeptDto> depts;

    private String name;

    private String dataScope;

    private Integer level;

    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoleDto roleDto = (RoleDto) o;
        return Objects.equals(id, roleDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
