package ${package}.service.dto;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import cn.hf.fuda.common.web.base.BaseDTO;
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
public class ${className}Dto extends BaseDTO implements Serializable {
<#if columns??>

    <#list columns as column>
    <#if column.changeColumnName != 'createTime' && column.changeColumnName != 'updateTime'>
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

    </#if>
    </#list>
</#if>
}
