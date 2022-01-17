package me.star.deploy.domain.entity;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.star.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Star Chou
 * @description /
 * @create 2022/1/17
 */
@Entity
@Data
@Table(name = "mnt_database")
public class Database extends BaseEntity implements Serializable {

    @Id
    @Column(name = "db_id")
    @ApiModelProperty(value = "ID", hidden = true)
    private String id;

    @ApiModelProperty(value = "数据库名称")
    private String name;

    @ApiModelProperty(value = "数据库连接地址")
    private String jdbcUrl;

    @ApiModelProperty(value = "数据库密码")
    private String pwd;

    @ApiModelProperty(value = "用户名")
    private String userName;

    public void copy(Database source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
