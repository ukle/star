package me.star.websocket;

import lombok.Data;

/**
 * @author sida_zhou
 * @description
 * @date 2022/1/5
 */

@Data
public class SocketMsg {
    private String msg;
    private MsgType msgType;

    public SocketMsg(String msg, MsgType msgType) {
        this.msg = msg;
        this.msgType = msgType;
    }
}
