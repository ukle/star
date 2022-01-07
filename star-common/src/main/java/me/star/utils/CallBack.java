package me.star.utils;

/**
 * @author sida_zhou
 * @description
 * @date 2022/1/6
 */
public interface CallBack {
    /**
     * 回调执行方法
     */
    void executor();

    /**
     * 本回调任务名称
     * @return /
     */
    default String getCallBackName() {
        return Thread.currentThread().getId() + ":" + this.getClass().getName();
    }
}


