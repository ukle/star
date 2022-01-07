package me.star.deploy.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.star.deploy.domain.DeployQueryCriteria;
import me.star.deploy.domain.entity.Deploy;
import me.star.deploy.service.DeployService;
import me.star.utils.FileUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

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

    private final String fileSavePath = FileUtil.getTmpDirPath() + "/";
    private final DeployService deployService;

    @ApiOperation(value = "查询部署")
    @GetMapping
    public ResponseEntity<Object> query(DeployQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(deployService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "新增部署")
    @PostMapping
    public ResponseEntity<Object> create(@Validated @RequestBody Deploy resources) {
        deployService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "修改部署")
    @PutMapping
    public ResponseEntity<Object> update(@Validated @RequestBody Deploy resources) {
        deployService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "删除部署")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Set<Long> ids) {
        deployService.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "上传文件部署")
    @PostMapping(value = "/upload")
    public ResponseEntity<Object> upload(@RequestBody MultipartFile file, HttpServletRequest request) throws Exception {
        Long id = Long.valueOf(request.getParameter("id"));
        String fileName = "";
        if (file != null) {
            fileName = file.getOriginalFilename();
            File deployFile = new File(fileSavePath + fileName);
            FileUtil.del(deployFile);
            file.transferTo(deployFile);
            //文件下一步要根据文件名字来
            deployService.deploy(fileSavePath + fileName, id);
        } else {
            System.out.println("没有找到相对应的文件");
        }
        System.out.println("文件上传的原名称为:" + Objects.requireNonNull(file).getOriginalFilename());
        Map<String, Object> map = new HashMap<>(2);
        map.put("errno", 0);
        map.put("id", fileName);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "服务运行状态")
    @PostMapping(value = "/serverStatus")
    public ResponseEntity<Object> serverStatus(@Validated @RequestBody Deploy resources) {
        String result = deployService.serverStatus(resources);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "启动服务")
    @PostMapping(value = "/startServer")
    public ResponseEntity<Object> startServer(@Validated @RequestBody Deploy resources) {
        String result = deployService.startServer(resources);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "停止服务")
    @PostMapping(value = "/stopServer")
    public ResponseEntity<Object> stopServer(@Validated @RequestBody Deploy resources) {
        String result = deployService.stopServer(resources);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
