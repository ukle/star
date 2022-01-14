package me.star.deploy.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.star.deploy.domain.entity.App;
import me.star.deploy.service.AppService;
import me.star.deploy.service.dto.AppQueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * @author sida_zhou
 * @description
 * @date 2022/1/14
 */

@RestController
@RequiredArgsConstructor
@Api(tags = "运维：应用管理")
@RequestMapping("/api/app")
public class AppController {

    private final AppService appService;

    @ApiOperation("导出应用数据")
    @GetMapping(value = "/download")
    public void exportApp(HttpServletResponse response, AppQueryCriteria criteria) throws IOException {
        appService.download(appService.queryAll(criteria), response);
    }

    @ApiOperation(value = "查询应用")
    @GetMapping
    public ResponseEntity<Object> queryApp(AppQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(appService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "新增应用")
    @PostMapping
    public ResponseEntity<Object> createApp(@Validated @RequestBody App resources) {
        appService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "修改应用")
    @PutMapping
    public ResponseEntity<Object> updateApp(@Validated @RequestBody App resources) {
        appService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "删除应用")
    @DeleteMapping
    public ResponseEntity<Object> deleteApp(@RequestBody Set<Long> ids) {
        appService.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
