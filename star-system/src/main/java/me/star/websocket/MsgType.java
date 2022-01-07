package me.star.websocket;

/**
 * @author sida_zhou
 * @description
 * @date 2022/1/5
 */
public enum MsgType {
    /** 连接 */
    CONNECT,
    /** 关闭 */
    CLOSE,
    /** 信息 */
    INFO,
    /** 错误 */
    ERROR
}
