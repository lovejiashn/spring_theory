package com.jiashn.scope.scope;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * @author jiangjs
 * @date 2023-03-12 10:53
 * @description 类的scope-session,即在http session中只创建一个bean实例，且生命周期与session一致
 */
@Scope(value = "session")
@Slf4j
@Component
public class ScopeForSession {

    @PreDestroy
    public void destroy(){
        log.info("......session 作用域 注销");
    }
}
