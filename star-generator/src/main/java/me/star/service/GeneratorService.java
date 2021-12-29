package me.star.service;

import me.star.domain.vo.ColumnInfo;
import me.star.domain.vo.GenConfig;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author sida_zhou
 * @description
 * @date 2021/12/24
 */
public interface GeneratorService {

    /**
     * 查询数据库元数据
     *
     * @param name     表名
     * @param startEnd 分页参数
     * @return /
     */
    Object getTables(String name, int[] startEnd);

    /**
     * 查询数据库的表字段数据数据
     *
     * @param table /
     * @return /
     */
    List<ColumnInfo> query(String table);

    /**
     * 保持数据
     *
     * @param columnInfos /
     */
    void save(List<ColumnInfo> columnInfos);

    /**
     * 代码生成
     * @param genConfig 配置信息
     * @param columns 字段信息
     */
    void generator(GenConfig genConfig, List<ColumnInfo> columns);

    /**
     * 预览
     * @param genConfig 配置信息
     * @param columns 字段信息
     * @return /
     */
    ResponseEntity<Object> preview(GenConfig genConfig, List<ColumnInfo> columns);

    /**
     * 打包下载
     * @param genConfig 配置信息
     * @param columns 字段信息
     * @param request /
     * @param response /
     */
    void download(GenConfig genConfig, List<ColumnInfo> columns, HttpServletRequest request, HttpServletResponse response);

}
