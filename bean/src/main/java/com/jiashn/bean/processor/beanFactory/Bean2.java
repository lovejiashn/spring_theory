package com.jiashn.bean.processor.beanFactory;

/**
 * @author jiangjs
 * @date 2023-03-07 6:13
 * @description
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Bean2 {
    public Bean2(){
        log.info("bean2 被 Spring 管理了");
    }
}
