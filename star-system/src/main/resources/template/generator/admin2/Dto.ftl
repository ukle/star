package ${package}.service.dto;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import cn.hf.fuda.common.web.base.BaseDTO;
<#if hasBigDecimal>
import java.math.BigDecimal;
</#if>
<#if hasLocalDateTime>
import java.time.LocalDateTime;
</#if>
import java.io.Serializable;

/**
* @author ${author}
* @date ${date}
**/
@Data
public class ${className}Dto extends BaseDTO implements Serializable {
<#if columns??>

    <#list columns as column>
    <#if column.changeColumnName != 'createTime' && column.changeColumnName != 'updateTime' && column.changeColumnName != 'createBy' && column.changeColumnName != 'updateBy'>
    <#if column.remark != ''>
    @ApiModelProperty(value = "${column.remark}")
    </#if>
    private ${column.columnType} ${column.changeColumnName};

    </#if>
    </#list>
</#if>
}
