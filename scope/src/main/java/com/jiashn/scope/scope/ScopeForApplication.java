package com.jiashn.scope.scope;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * @author jiangjs
 * @date 2023-03-12 10:56
 * @description 类的scope-application，即应用程序域，在整个应用程序中只创建一个bean实例。生命周期与应用程序一致。
 */
@Scope(value = "application")
@Component
@Slf4j
public class ScopeForApplication {
    @PreDestroy
    public void destroy(){
        log.info("......application 作用域 注销");
    }
}
