package me.star.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.star.system.dto.UserDto;
import me.star.utils.EncryptUtils;
import me.star.utils.StringUtils;

import java.util.Date;

/**
 * @author Star Chou
 * @description /
 * @create 2022/1/8 19:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnlineUserDto {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 岗位
     */
    private String dept;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * IP
     */
    private String ip;

    /**
     * 地址
     */
    private String address;

    /**
     * token
     */
    private String key;

    /**
     * 登录时间
     */
    private Date loginTime;

    public OnlineUserDto(JwtUserDto jwtUserDto, String browser, String ip, String token) throws Exception {
        UserDto user = jwtUserDto.getUser();
        this.userName = jwtUserDto.getUsername();
        this.nickName = user.getNickName();
        this.dept = user.getDept().getName();
        this.address = StringUtils.getCityInfo(ip);
        this.browser = browser;
        this.key = EncryptUtils.desEncrypt(token);
        this.loginTime = new Date();
    }
}

