package com.jiashn.scope.lapse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author jiangjs
 * @date 2023-03-14 5:37
 * @description 创建单例bean
 */
@Component
@Slf4j
public class FirstClass {

    @Lazy
    @Autowired
    private PrototypeBean bean;

    @Autowired
    private ProxyMethodBean methodBean;

    @Autowired
    private ObjectFactory<ObjectFactoryBean> factory;

    @Autowired
    private ApplicationContext context;

    public PrototypeBean printBean(){
        return bean;
    }

    public ProxyMethodBean getMethodBean() {
        return methodBean;
    }

    public ObjectFactoryBean getFactory() {
        return factory.getObject();
    }

    public ObjectFactoryBean getContext() {
        return context.getBean(ObjectFactoryBean.class);
    }
}
