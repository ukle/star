package me.star.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.star.ShufazidianService;
import me.star.domain.ShufazidianRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author Star Chou
 * @create 2021/12/25 20:45
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tools")
@Api(tags = "工具：书法字典")
public class ShufazidianController {

    private final ShufazidianService shufazidianService;

    @ApiOperation("长句集字")
    @PostMapping(value = "/long/collect", produces="text/json;charset=utf-8")
    public String load(@RequestBody ShufazidianRequestDto requestDto) throws IOException {
        return shufazidianService.loadHtml(requestDto);
    }
}
