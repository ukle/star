package me.star.deploy.service.dto;

import lombok.Data;
import me.star.base.BaseDTO;

import java.io.Serializable;

/**
 * @author Star Chou
 * @description /
 * @create 2022/1/17
 */
@Data
public class DatabaseDto extends BaseDTO implements Serializable {

    /**
     * id
     */
    private String id;

    /**
     * 数据库名称
     */
    private String name;

    /**
     * 数据库连接地址
     */
    private String jdbcUrl;

    /**
     * 数据库密码
     */
    private String pwd;

    /**
     * 用户名
     */
    private String userName;
}
