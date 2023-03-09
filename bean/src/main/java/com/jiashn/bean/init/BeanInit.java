package com.jiashn.bean.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author jiangjs
 * @date 2023-03-10 6:14
 * @description 实现InitializingBean,PostConstruct初始化bean
 */
@Slf4j
public class BeanInit implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("InitializingBean 初始化......");
    }

    @PostConstruct
    public void init(){
        log.info("PostConstruct 初始化......");
    }

    public void initMethod(){
        log.info("使用 initMethod 初始化......");
    }
}
