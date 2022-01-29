package me.star.system.service;

import me.star.system.domain.EmailVo;

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

    /**
     * 发送验证码
     * @param email /
     * @param key /
     * @return /
     */
    EmailVo sendEmail(String email, String key);
}
