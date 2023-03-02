package com.jiashn.bean.processor;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author jiangjs
 * @date 2023-03-03 6:08
 * @description application常用后处理器
 */
public class ProcessorUse {
    public static void main(String[] args) {
        //创建ApplicationContext，GenericApplicationContext是一个相对干净的容器
        GenericApplicationContext context = new GenericApplicationContext();

        //将bean注入
        context.registerBean("bean1",Bean1.class);
        context.registerBean("bean2",Bean2.class);
        context.registerBean("bean3",Bean3.class);
        context.registerBean("bean4",Bean4.class);


        //设置AutowireCandidateResolver使之具有解析@Value字符串功能
        context.getDefaultListableBeanFactory().setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());
        //注入AutowiredAnnotationBeanPostProcessor后处理，解析@Autowired @Value
        context.registerBean(AutowiredAnnotationBeanPostProcessor.class);
        //注入CommonAnnotationBeanPostProcessor后处理器，解析@Resource @PostConstruct @PreDestroy
        context.registerBean(CommonAnnotationBeanPostProcessor.class);
        //注入SpringBoot中的ConfigurationPropertiesBindingPostProcessor后处理器，解析@ConfigurationProperties
        ConfigurationPropertiesBindingPostProcessor.register(context.getDefaultListableBeanFactory());
        //初始化容器,执行BeanFactory后处理器，执行Bean后处理器，初始化所有单例
        context.refresh();

        System.out.println(context.getBean(Bean4.class));
        //销毁容器
        context.close();


    }
}
