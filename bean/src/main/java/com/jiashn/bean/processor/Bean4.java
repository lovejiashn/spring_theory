package com.jiashn.bean.processor;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author jiangjs
 * @date 2023-03-03 6:03
 * @description
 */
@ConfigurationProperties(prefix = "java")
@Slf4j
@Data
public class Bean4 {
    private String home;
    private String version;
}
