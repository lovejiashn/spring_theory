package com.jiashn.scope.lapse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author jiangjs
 * @date 2023-03-14 6:24
 * @description
 */
@Component
@Scope(value = "prototype")
public class ObjectFactoryBean {
}
