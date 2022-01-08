package me.star.system.service;

import me.star.system.dto.UserDto;

import java.util.List;

/**
 * @author Star Chou
 * @description /
 * @create 2022/1/8 20:27
 */
public interface DataService {

    /**
     * 获取数据权限
     * @param user /
     * @return /
     */
    List<Long> getDeptIds(UserDto user);
}
