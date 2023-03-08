package com.jiashn.bean.processor.beanFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jiangjs
 * @date 2023-03-07 6:14
 * @description
 */
@Slf4j
public class Bean1 {
    public Bean1(){
        log.info("bean1 被 Spring 管理了");
    }
}
