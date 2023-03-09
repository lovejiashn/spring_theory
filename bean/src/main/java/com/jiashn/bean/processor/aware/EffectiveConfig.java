package com.jiashn.bean.processor.aware;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jiangjs
 * @date 2023-03-10 5:54
 * @description 实现接口InitializingBean, ApplicationContextAware
 */
@Configuration
@Slf4j
public class EffectiveConfig implements InitializingBean, ApplicationContextAware {
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("初始化 ......");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("注入 applicationContext ......");
    }

    @Bean
    public BeanFactoryPostProcessor processor(){
        return beanfactory -> {
            log.info("执行 EffectiveConfig  processor ......");
        };
    }
}
