package me.star.zheshan.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.copier.CopyOptions;
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
@Table(name="zheshan")
public class Zheshan implements Serializable {

    @Id
    @Column(name = "zs_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键id")
    private Long zsId;

    @Column(name = "zs_type",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "折扇类型：玉竹、棕竹、云妃")
    private String zsType;

    @Column(name = "zs_name")
    @ApiModelProperty(value = "折扇名称")
    private String zsName;

    @Column(name = "zs_length",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "折扇尺寸")
    private String zsLength;

    @Column(name = "zs_touxing",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "折扇头型")
    private String zsTouxing;

    @Column(name = "zs_paikou",nullable = false)
    @NotNull
    @ApiModelProperty(value = "折扇排口：1.5，1.8，2.0，3.0")
    private Float zsPaikou;

    @Column(name = "zs_jianbu")
    @ApiModelProperty(value = "折扇肩部：直肩、美人肩、庙门肩")
    private String zsJianbu;

    @Column(name = "zs_tangding")
    @ApiModelProperty(value = "折扇烫钉：黑牛角、白牛角")
    private String zsTangding;

    @Column(name = "zs_fangshu",nullable = false)
    @NotNull
    @ApiModelProperty(value = "折扇方数：16、18方")
    private Integer zsFangshu;

    @Column(name = "zs_sale_in",nullable = false)
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
    private Timestamp createTime;

    @Column(name = "update_time")
    @ApiModelProperty(value = "更新时间")
    private Timestamp updateTime;

    public void copy(Zheshan source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}