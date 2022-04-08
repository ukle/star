package ${package}.service.dto;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import cn.hf.fuda.activity.repository.entity.${className};
<#if hasLocalDateTime>
import java.time.LocalDateTime;
</#if>
<#if notNull>
import javax.validation.constraints.NotNull;
</#if>
<#if hasBigDecimal>
import java.math.BigDecimal;
</#if>
import java.io.Serializable;
import javax.validation.constraints.Max;

/**
* @author ${author}
* @date ${date}
**/
@Data
public class ${className}ForCreate implements Serializable {

<#if columns??>
    <#list columns as column>
    <#if column.changeColumnName != 'id'>
    <#if column.remark != '' && column.istNotNull == true>
    @ApiModelProperty(value = "${column.remark}", required = true)
    @NotNull
    </#if>
    <#if column.remark != '' && column.istNotNull == false>
    @ApiModelProperty(value = "${column.remark}")
    </#if>
    <#if column.maxLength?exists>
    <#if column.maxLength != 65535>
    @Max(value = ${column.maxLength}, message = "${column.remark}长度不能超过${column.maxLength}")
    </#if>
    </#if>
    private ${column.columnType} ${column.changeColumnName};

    </#if>
    </#list>
</#if>

    public ${className} to${className}() {
        ${className} entity = new ${className}();
        //todo 入参赋值给实体
        return entity;
    }
}
