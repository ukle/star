package me.star.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Star Chou
 * @create 2022/1/3 21:14
 */
@Configuration
@Data
public class RedisProperties {

    private int redisPort;
    private String redisHost;
    private final String password;
    private final int database;

    public RedisProperties(
            @Value("${spring.redis.port}") int redisPort,
            @Value("${spring.redis.host}") String redisHost,
            @Value("${spring.redis.password:}") String password,
            @Value("${spring.redis.database:0}") int database) {
        this.redisPort = redisPort;
        this.redisHost = redisHost;
        this.password = password;
        this.database = database;
    }
}
