package me.star.security.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Star Chou
 * @description /
 * @create 2022/1/8 19:32
 */
@Data
public class AuthUserDto {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String code;

    private String uuid = "";
}
