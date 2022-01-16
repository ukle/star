package me.star.system.domain;

import lombok.Data;

/**
 * @author Star Chou
 * @description /
 * @create 2022/1/14
 */
@Data
public class UserPassVo {

    private String oldPass;

    private String newPass;
}
