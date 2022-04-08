package ${package}.service.dto;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import cn.hf.fuda.activity.repository.entity.${className};
<#if hasLocalDateTime>
import java.time.LocalDateTime;
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
public class ${className}ForUpdate implements Serializable {
<#if columns??>
    <#list columns as column>

    <#if column.remark != ''>
    @ApiModelProperty(value = "${column.remark}")
    </#if>
    <#if column.maxLength?exists>
    <#if column.maxLength != 65535>
    @Max(value = ${column.maxLength}, message = "${column.remark}长度不能超过${column.maxLength}")
    </#if>
    </#if>
    private ${column.columnType} ${column.changeColumnName};
    </#list>
</#if>

    public ${className} update(${className} entity) {
        // todo 更新实体数据
        //if (this.enable != null) {
        //    entity.setEnable(this.enable);
        //}

        return entity;
    }
}
