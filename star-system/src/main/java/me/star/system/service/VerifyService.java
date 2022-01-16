package me.star.system.service;

/**
 * @author Star Chou
 * @description /
 * @create 2022/1/14
 */
public interface VerifyService {

    /**
     * 验证
     * @param code /
     * @param key /
     */
    void validated(String key, String code);
}
