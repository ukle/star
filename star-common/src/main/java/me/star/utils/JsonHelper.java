package me.star.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author sida_zhou
 * @description
 * @date 2022/1/5
 */

@Slf4j
public class JsonHelper {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {

        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        //objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


        JavaTimeModule javaTimeModule = new JavaTimeModule();
        //日期序列化
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));

        //日期反序列化
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        objectMapper.registerModule(javaTimeModule);
    }

    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            log.error("对象转换json失败,errMsg:{}", e.getMessage(), e);
        }
        return null;
    }

    public static <T> T toBean(String json, Class<T> clz) {
        try {
            return objectMapper.readValue(json, clz);
        } catch (Exception e) {
            log.error("json转换对象失败,errMsg:{}", e.getMessage(), e);
        }
        return null;
    }

}

