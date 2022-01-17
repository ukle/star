package me.star.system.service;

import java.util.Map;

/**
 * @author Star Chou
 * @description /
 * @create 2022/1/17
 */
public interface MonitorService {
    /**
     * 查询数据分页
     * @return Map<String,Object>
     */
    Map<String,Object> getServers();
}
