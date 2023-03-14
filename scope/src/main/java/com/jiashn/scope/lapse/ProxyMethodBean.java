package com.jiashn.scope.lapse;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * @author jiangjs
 * @date 2023-03-14 6:13
 * @description
 */
@Component
@Scope(value = "prototype",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ProxyMethodBean {
}
