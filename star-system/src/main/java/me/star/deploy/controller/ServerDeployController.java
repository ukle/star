package me.star.deploy.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.star.deploy.domain.entity.ServerDeploy;
import me.star.deploy.service.ServerDeployService;
import me.star.deploy.service.dto.ServerDeployQueryCriteria;
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
@Api(tags = "运维：服务器管理")
@RequiredArgsConstructor
@RequestMapping("/api/serverDeploy")
public class ServerDeployController {

    private final ServerDeployService serverDeployService;

    @ApiOperation(value = "查询服务器")
    @GetMapping
    public ResponseEntity<Object> queryServerDeploy(ServerDeployQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(serverDeployService.queryAll(criteria,pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "新增服务器")
    @PostMapping
    public ResponseEntity<Object> createServerDeploy(@Validated @RequestBody ServerDeploy resources){
        serverDeployService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "修改服务器")
    @PutMapping
    public ResponseEntity<Object> updateServerDeploy(@Validated @RequestBody ServerDeploy resources){
        serverDeployService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteServerDeploy(@RequestBody Set<Long> ids){
        serverDeployService.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "测试连接服务器")
    @PostMapping("/testConnect")
    public ResponseEntity<Object> testConnectServerDeploy(@Validated @RequestBody ServerDeploy resources){
        return new ResponseEntity<>(serverDeployService.testConnect(resources),HttpStatus.CREATED);
    }
}
