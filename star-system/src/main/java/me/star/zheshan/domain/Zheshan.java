package me.star.zheshan.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.copier.CopyOptions;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
 * @author Star Chou
 * @date 2022-01-15
 **/
@Entity
@Data
@Table(name = "zheshan")
@EntityListeners(AuditingEntityListener.class)
public class Zheshan implements Serializable {

    @Id
    @Column(name = "zs_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键id")
    private Long zsId;

    @Column(name = "zs_type", nullable = false)
    @NotBlank
    @ApiModelProperty(value = "折扇类型：玉竹、棕竹、云妃")
    private String zsType;

    @Column(name = "zs_length", nullable = false)
    @NotBlank
    @ApiModelProperty(value = "折扇尺寸")
    private String zsLength;

    @Column(name = "zs_touxing", nullable = false)
    @NotBlank
    @ApiModelProperty(value = "折扇头型")
    private String zsTouxing;

    @Column(name = "zs_paikou", nullable = false)
    @NotNull
    @ApiModelProperty(value = "折扇排口：1.5，1.8，2.0，3.0")
    private String zsPaikou;

    @Column(name = "zs_jianbu")
    @ApiModelProperty(value = "折扇肩部：直肩、美人肩、庙门肩")
    private String zsJianbu;

    @Column(name = "zs_tangding")
    @ApiModelProperty(value = "折扇烫钉：黑牛角、白牛角")
    private String zsTangding;

    @Column(name = "zs_fangshu", nullable = false)
    @NotNull
    @ApiModelProperty(value = "折扇方数：16、18方")
    private String zsFangshu;

    @Column(name = "zs_sale_in", nullable = false)
    @NotNull
    @ApiModelProperty(value = "折扇买入价")
    private Integer zsSaleIn;

    @Column(name = "zs_sale_out")
    @ApiModelProperty(value = "折扇卖出价")
    private Integer zsSaleOut;

    @Column(name = "zs_author")
    @ApiModelProperty(value = "折扇作者")
    private String zsAuthor;

    @Column(name = "zs_desc")
    @ApiModelProperty(value = "折扇描述")
    private String zsDesc;

    @Column(name = "create_time")
    @ApiModelProperty(value = "创建日期")
    @CreatedDate
    private Timestamp createTime;

    @Column(name = "update_time")
    @ApiModelProperty(value = "更新时间")
    @LastModifiedDate
    private Timestamp updateTime;

    @Column(name = "sale_status")
    @ApiModelProperty(value = "销售状态")
    private String saleStatus;

    public void copy(Zheshan source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
