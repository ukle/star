package ${package}.service.dto;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import cn.hf.fuda.activity.repository.entity.${className};
<#if hasTimestamp>
import java.sql.Timestamp;
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
public class ${className}ForUpdate implements Serializable {
<#if columns??>
    <#list columns as column>

    <#if column.remark != ''>
    @ApiModelProperty(value = "${column.remark}", required = false)
    </#if>
    <#if column.columnKey = 'PRI'>
        <#if !auto && pkColumnType = 'Long'>
            /** 防止精度丢失 */
            @JSONField(serializeUsing = ToStringSerializer.class)
        </#if>
    </#if>
    private ${column.columnType} ${column.changeColumnName};
    </#list>
</#if>

    public ${className} update(${className} entity) {
        // 更新数据
        //if (this.enable != null) {
        //    entity.setEnable(this.enable);
        //}

        return entity;
    }
}
