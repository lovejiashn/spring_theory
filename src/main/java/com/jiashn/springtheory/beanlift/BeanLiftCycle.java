package com.jiashn.springtheory.beanlift;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author jiangjs
 * @date 2023-02-28 6:13
 * @description: Bean的生命周期
 */
@Component
@Slf4j
public class BeanLiftCycle {
    public BeanLiftCycle(){
        log.info(">>>>>>>>>实例化>>>>>>>>>");
    }

    @Autowired
    public void autowire(@Value("${JAVA_HOME}") String name) {
        log.info(">>>>>>>依赖注入：{}", name);
    }

    @PostConstruct
    public void init() {
        log.info(">>>>>>>>>>>初始化");
    }

    @PreDestroy
    public void destroy() {
        log.info(">>>>>>>>>>>销毁");
    }
}
