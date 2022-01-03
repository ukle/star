package me.star.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author Star Chou
 * @create 2022/1/3 20:15
 */
public class ThrowableUtil {

    /**
     * 获取堆栈信息
     */
    public static String getStackTrace(Throwable throwable){
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }
}
