package ${package}.domain;

import cn.hf.fuda.common.web.base.BaseEntity;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
<#if hasDateAnnotation>
import javax.persistence.Entity;
import javax.persistence.Table;
</#if>
<#if hasBigDecimal>
import java.math.BigDecimal;
</#if>
import java.io.Serializable;

/**
* @author ${author}
* @date ${date}
**/
@Entity
@Data
@Table(name="${tableName}")
public class ${className} extends BaseEntity implements Serializable {

<#if columns??>
    <#list columns as column>
    <#if column.changeColumnName != 'createTime' && column.changeColumnName != 'updateTime'>
    <#if column.columnKey = 'PRI'>
    @Id
    <#if auto>
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    </#if>
    </#if>
    @Column(name = "${column.columnName}"<#if column.columnKey = 'UNI'>,unique = true</#if><#if column.istNotNull && column.columnKey != 'PRI'>,nullable = false</#if>)
    <#if column.istNotNull && column.columnKey != 'PRI'>
    <#if column.columnType = 'String'>
    @NotBlank
    <#else>
    @NotNull
    </#if>
    </#if>
    <#if (column.dateAnnotation)?? && column.dateAnnotation != ''>
    <#if column.dateAnnotation = 'CreationTimestamp'>
    @CreationTimestamp
    <#else>
    @UpdateTimestamp
    </#if>
    </#if>
    <#if column.remark != ''>
    @ApiModelProperty(value = "${column.remark}")
    <#else>
    @ApiModelProperty(value = "${column.changeColumnName}")
    </#if>
    private ${column.columnType} ${column.changeColumnName};
    </#if>
    </#list>
</#if>

}
