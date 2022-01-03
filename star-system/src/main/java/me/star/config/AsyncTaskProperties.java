package me.star.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Star Chou
 * @create 2022/1/3 22:08
 */

@Data
@Component
@ConfigurationProperties(prefix = "task.pool")
public class AsyncTaskProperties {

    private int corePoolSize;

    private int maxPoolSize;

    private int keepAliveSeconds;

    private int queueCapacity;
}
