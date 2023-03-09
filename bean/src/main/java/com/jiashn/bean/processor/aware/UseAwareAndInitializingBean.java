package com.jiashn.bean.processor.aware;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author jiangjs
 * @date 2023-03-10 5:29
 * @description 使用Aware和InitializingBean
 */
public class UseAwareAndInitializingBean {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
       // context.registerBean("lapseConfig",LapseConfig.class);
        context.registerBean("effectiveConfig",EffectiveConfig.class);
        context.registerBean(AutowiredAnnotationBeanPostProcessor.class);
        context.registerBean(CommonAnnotationBeanPostProcessor.class);
        context.registerBean(ConfigurationClassPostProcessor.class);
        context.refresh();
        context.close();
    }
}
