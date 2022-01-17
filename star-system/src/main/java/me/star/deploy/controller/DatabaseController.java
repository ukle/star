package me.star.deploy.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.star.deploy.domain.entity.Database;
import me.star.deploy.service.DatabaseService;
import me.star.deploy.service.dto.DatabaseDto;
import me.star.deploy.service.dto.DatabaseQueryCriteria;
import me.star.deploy.utils.SqlUtils;
import me.star.exception.BadRequestException;
import me.star.utils.FileUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * @author Star Chou
 * @description /
 * @create 2022/1/17
 */
@Api(tags = "运维：数据库管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/database")
public class DatabaseController {

    private final String fileSavePath = FileUtil.getTmpDirPath() + "/";
    private final DatabaseService databaseService;

    @ApiOperation("导出数据库数据")
    @GetMapping(value = "/download")
    public void exportDatabase(HttpServletResponse response, DatabaseQueryCriteria criteria) throws IOException {
        databaseService.download(databaseService.queryAll(criteria), response);
    }

    @ApiOperation(value = "查询数据库")
    @GetMapping
    public ResponseEntity<Object> queryDatabase(DatabaseQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(databaseService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "新增数据库")
    @PostMapping
    public ResponseEntity<Object> createDatabase(@Validated @RequestBody Database resources) {
        databaseService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "修改数据库")
    @PutMapping
    public ResponseEntity<Object> updateDatabase(@Validated @RequestBody Database resources) {
        databaseService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "删除数据库")
    @DeleteMapping
    public ResponseEntity<Object> deleteDatabase(@RequestBody Set<String> ids) {
        databaseService.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "测试数据库链接")
    @PostMapping("/testConnect")
    public ResponseEntity<Object> testConnect(@Validated @RequestBody Database resources) {
        return new ResponseEntity<>(databaseService.testConnection(resources), HttpStatus.CREATED);
    }

    @ApiOperation(value = "执行SQL脚本")
    @PostMapping(value = "/upload")
    public ResponseEntity<Object> uploadDatabase(@RequestBody MultipartFile file, HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        DatabaseDto database = databaseService.findById(id);
        String fileName;
        if (database != null) {
            fileName = file.getOriginalFilename();
            File executeFile = new File(fileSavePath + fileName);
            FileUtil.del(executeFile);
            file.transferTo(executeFile);
            String result = SqlUtils.executeFile(database.getJdbcUrl(), database.getUserName(), database.getPwd(), executeFile);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            throw new BadRequestException("Database not exist");
        }
    }
}
