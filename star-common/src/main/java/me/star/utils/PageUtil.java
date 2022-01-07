package me.star.utils;

import org.springframework.data.domain.Page;

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

    /**
     * Page 数据处理，预防redis反序列化报错
     */
    public static Map<String, Object> toPage(Page page) {
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", page.getContent());
        map.put("totalElements", page.getTotalElements());
        return map;
    }
}
