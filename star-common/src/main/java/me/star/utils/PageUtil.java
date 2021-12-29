package me.star.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author sida_zhou
 * @description
 * @date 2021/12/24
 */
public class PageUtil extends cn.hutool.core.util.PageUtil {

    /**
     * 自定义分页
     */
    public static Map<String, Object> toPage(Object object, Object totalElements) {
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", object);
        map.put("totalElements", totalElements);
        return map;
    }

}
