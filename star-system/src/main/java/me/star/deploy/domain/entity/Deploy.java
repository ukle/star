package me.star.deploy.domain.entity;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.star.base.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author sida_zhou
 * @description
 * @date 2022/1/4
 */
@Entity
@Table(name = "mnt_deploy")
@Data
public class Deploy extends BaseEntity implements Serializable {

    @Id
    @Column(name = "deploy_id")
    @ApiModelProperty(value = "ID", hidden = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @ApiModelProperty(name = "服务器", hidden = true)
    @JoinTable(name = "mnt_deploy_server",
            joinColumns = {@JoinColumn(name = "deploy_id", referencedColumnName = "deploy_id")},
            inverseJoinColumns = {@JoinColumn(name = "server_id", referencedColumnName = "server_id")})
    private Set<ServerDeploy> deploys;

    @ManyToOne
    @JoinColumn(name = "app_id")
    @ApiModelProperty(value = "应用编号")
    private App app;

    public void copy(Deploy source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
