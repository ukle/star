package ${package}.service.dto;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import cn.hf.fuda.activity.repository.entity.${className};
<#if hasTimestamp>
import java.sql.Timestamp;
</#if>
<#if notNull>
import javax.validation.constraints.NotNull;
</#if>
<#if hasBigDecimal>
import java.math.BigDecimal;
</#if>
import java.io.Serializable;
<#if !auto && pkColumnType = 'Long'>
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
</#if>

/**
* @author ${author}
* @date ${date}
**/
@Data
public class ${className}ForCreate implements Serializable {
<#if columns??>
    <#list columns as column>

    <#if column.remark != '' && column.istNotNull == true>
    @ApiModelProperty(value = "${column.remark}", required = true)
    @NotNull
    </#if>
    <#if column.remark != '' && column.istNotNull == false>
    @ApiModelProperty(value = "${column.remark}")
    </#if>
    <#if column.columnKey = 'PRI'>
    <#if !auto && pkColumnType = 'Long'>
    /** 防止精度丢失 */
    @JSONField(serializeUsing = ToStringSerializer.class)
    </#if>
    </#if>
    <#if column.maxLength?exists>
    @Max(value = ${column.maxLength}, message = "${column.remark}长度不能超过${column.maxLength}")
    </#if>
    private ${column.columnType} ${column.changeColumnName};
    </#list>
</#if>

    public ${className} to${className}() {
        ${className} entity = new ${className}();
        //todo
        return entity;
    }
}
