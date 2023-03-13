package com.jiashn.scope.scope;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * @author jiangjs
 * @date 2023-03-12 10:48
 * @description 类的scope-request，即每个http请求时都有各自的bean实例
 */
@Scope(value = "request")
@Component
@Slf4j
public class ScopeForRequest {

    @PreDestroy
    public void destroy(){
        log.info("......request 作用域 注销");
    }
}
