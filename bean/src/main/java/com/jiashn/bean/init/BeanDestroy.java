package com.jiashn.bean.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;

import javax.annotation.PreDestroy;

/**
 * @author jiangjs
 * @date 2023-03-10 6:30
 * @description 实现DisposableBean、@PreDestroy
 */
@Slf4j
public class BeanDestroy implements DisposableBean {
    @Override
    public void destroy() throws Exception {
        log.info("DisposableBean 销毁......");
    }

    @PreDestroy
    public void preDestroy(){
        log.info("@PreDestroy 销毁......");
    }

    public void destroyMethod(){
        log.info("destroyMethod 销毁......");
    }

}
