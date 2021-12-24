package com.star.service;

/**
 * @author sida_zhou
 * @description
 * @date 2021/12/24
 */
public interface GeneratorService {

    /**
     * 查询数据库元数据
     * @param name 表名
     * @param startEnd 分页参数
     * @return /
     */
    Object getTables(String name, int[] startEnd);

}
