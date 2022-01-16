package me.star.zheshan.controller;

import me.star.zheshan.domain.Zheshan;
import me.star.zheshan.service.ZheshanService;
import me.star.zheshan.service.dto.ZheshanQueryCriteria;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author Star Chou
* @date 2022-01-15
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "折扇管理")
@RequestMapping("/api/zheshan")
public class ZheshanController {

    private final ZheshanService zheshanService;

    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    public void download(HttpServletResponse response, ZheshanQueryCriteria criteria) throws IOException {
        zheshanService.download(zheshanService.queryAll(criteria), response);
    }

    @GetMapping
    @ApiOperation("查询折扇")
    public ResponseEntity<Object> query(ZheshanQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zheshanService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("新增折扇")
    public ResponseEntity<Object> create(@Validated @RequestBody Zheshan resources){
        return new ResponseEntity<>(zheshanService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @ApiOperation("修改折扇")
    public ResponseEntity<Object> update(@Validated @RequestBody Zheshan resources){
        zheshanService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation("删除折扇")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Long[] ids) {
        zheshanService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
