package me.star.deploy.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.star.deploy.domain.DeployQueryCriteria;
import me.star.deploy.service.DeployService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sida_zhou
 * @description 部署管理
 * @date 2022/1/4
 */
@RestController
@Api(tags = "运维：部署管理")
@RequiredArgsConstructor
@RequestMapping("/api/deploy")
public class DeployController {

    private final DeployService deployService;

    @ApiOperation(value = "查询部署")
    @GetMapping
    public ResponseEntity<Object> query(DeployQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(deployService.queryAll(criteria, pageable), HttpStatus.OK);
    }
}
