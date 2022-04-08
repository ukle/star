package ${package}.controller;

import cn.hf.boot.web.PageResult;
import ${package}.service.${className}Service;
import ${package}.domain.dto.${className}Dto;
import ${package}.domain.dto.${className}ForCreate;
import ${package}.domain.dto.${className}ForUpdate;
import ${package}.domain.dto.${className}QueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @author ${author}
* @date ${date}
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "${apiAlias}管理")
@RequestMapping("/api/${changeClassName}")
public class ${className}Controller {

    private final ${className}Service ${changeClassName}Service;

    @PostMapping("/page/{pageIndex}/{pageSize}")
    @ApiOperation("分页查询${apiAlias}")
    public PageResult<${className}Dto> query(@Validated @RequestBody ${className}QueryCriteria criteria,
                                         @PathVariable("pageIndex") int pageIndex,
                                         @PathVariable("pageSize") int pageSize) {
        PageRequest page = PageRequest.of(pageIndex - 1, pageSize);
        return ${changeClassName}Service.queryAll(criteria, page);
    }

    @GetMapping("/list")
    @ApiOperation("查询${apiAlias}")
    public List<${className}Dto> query(${className}QueryCriteria criteria) {
        return ${changeClassName}Service.queryAll(criteria);
    }

    @PostMapping("/create")
    @ApiOperation("新增${apiAlias}")
    public ${className}Dto create(@Validated @RequestBody ${className}ForCreate forCreate) {
        return ${changeClassName}Service.create(forCreate);
    }

    @PostMapping("/update")
    @ApiOperation("修改${apiAlias}")
    public void update(@Validated @RequestBody ${className}ForUpdate forUpdate) {
        ${changeClassName}Service.update(forUpdate);
    }

    @ApiOperation("删除${apiAlias}")
    @PostMapping("/delete")
    public void delete(@RequestBody ${pkColumnType}[] ids) {
        ${changeClassName}Service.deleteAll(ids);
    }
}
