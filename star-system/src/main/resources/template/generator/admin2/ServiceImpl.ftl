package ${package}.service.impl;

import cn.hf.boot.web.PageResult;
import cn.hf.boot.web.utils.QueryHelp;
<#if columns??>
    <#list columns as column>
        <#if column.columnKey = 'UNI'>
            <#if column_index = 1>
import me.star.exception.EntityExistException;
            </#if>
        </#if>
    </#list>
</#if>
import cn.hf.fuda.common.web.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import ${package}.repository.${className}Repository;
import ${package}.repository.entity.${className};
import ${package}.service.${className}Service;
import ${package}.domain.dto.*;
import ${package}.service.mapstruct.${className}Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @description
* @author ${author}
* @date ${date}
**/
@Service
@RequiredArgsConstructor
public class ${className}ServiceImpl implements ${className}Service {

    private final ${className}Repository ${changeClassName}Repository;
    private final ${className}Mapper ${changeClassName}Mapper;

    @Override
    public PageResult<${className}Dto> queryAll(${className}QueryCriteria criteria, Pageable pageable){
        Page<${className}> page = ${changeClassName}Repository.findAll((root, criteriaQuery, cb) ->
                QueryHelp.getPredicate(root, criteria, cb), pageable);
        Page<${className}Dto> map = page.map(${changeClassName}Mapper::toDto);
        return new PageResult<>(map.getTotalElements(), map.getContent());
    }

    @Override
    public List<${className}Dto> queryAll(${className}QueryCriteria criteria){
        return ${changeClassName}Mapper.toDto(${changeClassName}Repository.findAll((root, criteriaQuery, cb)
            -> QueryHelp.getPredicate(root, criteria, cb)));
    }

    @Override
    @Transactional
    public ${className}Dto findById(${pkColumnType} ${pkChangeColName}) {
        ${className} ${changeClassName} = ${changeClassName}Repository.findById(${pkChangeColName}).orElseGet(${className}::new);
        ValidationUtil.isNull(${changeClassName}.get${pkCapitalColName}(), "${className}", "${pkChangeColName}", ${pkChangeColName});
        return ${changeClassName}Mapper.toDto(${changeClassName});
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ${className}Dto create(${className}ForCreate forCreate) {
        ${className} entity = forCreate.to${className}();
        return ${changeClassName}Mapper.toDto(${changeClassName}Repository.save(entity));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(${className}ForUpdate forUpdate) {
        ${className} ${changeClassName} = ${changeClassName}Repository.findById(forUpdate.get${pkCapitalColName}()).orElseGet(${className}::new);
        ValidationUtil.isNull( ${changeClassName}.get${pkCapitalColName}(), "${className}","id", forUpdate.get${pkCapitalColName}());
<#if columns??>
    <#list columns as column>
        <#if column.columnKey = 'UNI'>
        <#if column_index = 1>
        ${className} ${changeClassName}1 = null;
        </#if>
        ${changeClassName}1 = ${changeClassName}Repository.findBy${column.capitalColumnName}(resources.get${column.capitalColumnName}());
        if(${changeClassName}1 != null && !${changeClassName}1.get${pkCapitalColName}().equals(${changeClassName}.get${pkCapitalColName}())){
            throw new EntityExistException(${className}.class, "${column.columnName}", resources.get${column.capitalColumnName}());
        }
        </#if>
    </#list>
</#if>
        ${className} update = forUpdate.update(${changeClassName});
        ${changeClassName}Repository.save(update);
    }

    @Override
    public void deleteAll(${pkColumnType}[] ids) {
        for (${pkColumnType} ${pkChangeColName} : ids) {
            ${changeClassName}Repository.deleteById(${pkChangeColName});
        }
    }
}
