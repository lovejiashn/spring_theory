package com.jiashn.bean.processor.aware;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author jiangjs
 * @date 2023-03-10 5:32
 * @description 失效的配置
 */
//@Configuration
@Slf4j
public class LapseConfig {

    @Autowired
    private void setApplicationContext(ApplicationContext applicationContext){
        log.info("注入 applicationContext ......");
    }

    @PostConstruct
    public void init(){
        log.info("初始化......");
    }

    @Bean
    public BeanFactoryPostProcessor processor(){
       return beanfactory -> {
           log.info("执行 processor ......");
        };
    }
}
