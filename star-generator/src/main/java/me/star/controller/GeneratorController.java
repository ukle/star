package me.star.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.star.domain.vo.ColumnInfo;
import me.star.exception.BadRequestException;
import me.star.service.GenConfigService;
import me.star.service.GeneratorService;
import me.star.utils.PageUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
    private final GenConfigService genConfigService;

    @ApiOperation("查询数据库数据")
    @GetMapping(value = "/tables")
    public ResponseEntity<Object> queryTables(@RequestParam(defaultValue = "") String name,
                                              @RequestParam(defaultValue = "0") Integer page,
                                              @RequestParam(defaultValue = "10") Integer size) {
        int[] startEnd = PageUtil.transToStartEnd(page, size);
        return new ResponseEntity<>(generatorService.getTables(name, startEnd), HttpStatus.OK);
    }

    @ApiOperation("查询字段数据")
    @GetMapping(value = "/columns")
    public ResponseEntity<Object> queryColumns(@RequestParam String tableName) {
        List<ColumnInfo> columnInfos = generatorService.query(tableName);
        return new ResponseEntity<>(PageUtil.toPage(columnInfos, columnInfos.size()), HttpStatus.OK);
    }

    @ApiOperation("保存字段数据")
    @PutMapping
    public ResponseEntity<HttpStatus> save(@RequestBody List<ColumnInfo> columnInfos) {
        generatorService.save(columnInfos);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("同步字段数据")
    @PostMapping(value = "sync")
    public ResponseEntity<HttpStatus> sync(@RequestBody List<String> tables) {
        for (String table : tables) {
            generatorService.query(table);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("生成代码")
    @PostMapping(value = "/{tableName}/{type}")
    public ResponseEntity<Object> generator(@PathVariable String tableName, @PathVariable Integer type,
                                            HttpServletRequest request, HttpServletResponse response) {
        switch (type) {
            // 生成代码
            case 0:
                generatorService.generator(genConfigService.find(tableName), generatorService.query(tableName));
                break;
            // 预览
            case 1:
                return generatorService.preview(genConfigService.find(tableName), generatorService.query(tableName));
            // 下载
            case 2:
                generatorService.download(genConfigService.find(tableName), generatorService.query(tableName), request, response);
                break;
            default:
                throw new BadRequestException("没有这个选项");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
