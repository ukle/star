package com.star.controller;

import cn.hutool.core.util.PageUtil;
import com.star.service.GeneratorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sida_zhou
 * @description 代码生成管理
 * @date 2021/12/24
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/generator")
@Api(tags = "系统：代码生成管理")
public class GeneratorController {

    private final GeneratorService generatorService;

    @ApiOperation("查询数据库数据")
    @GetMapping(value = "/tables")
    public ResponseEntity<Object> queryTables(@RequestParam(defaultValue = "") String name,
                                              @RequestParam(defaultValue = "0")Integer page,
                                              @RequestParam(defaultValue = "10")Integer size){
        int[] startEnd = PageUtil.transToStartEnd(page, size);
        return new ResponseEntity<>(generatorService.getTables(name,startEnd), HttpStatus.OK);
    }
}
