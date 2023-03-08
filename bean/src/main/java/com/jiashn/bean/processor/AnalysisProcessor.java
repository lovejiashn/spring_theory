package com.jiashn.bean.processor;

import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.core.MethodParameter;
import org.springframework.core.env.StandardEnvironment;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author jiangjs
 * @date 2023-03-06 20:19
 * @description AutowiredAnnotationBeanPostProcessor执行过程
 */
public class AnalysisProcessor {
    public static void main(String[] args) throws Throwable {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.registerSingleton("bean2",new Bean2());
        factory.registerSingleton("bean3",new Bean3());
        //具有解析@Value数据功能
        factory.setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());
        // 设置解析 @Value 注解中的 ${} 表达式的解析器
        factory.addEmbeddedValueResolver(new StandardEnvironment()::resolvePlaceholders);
        AutowiredAnnotationBeanPostProcessor postProcessor = new AutowiredAnnotationBeanPostProcessor();
        postProcessor.setBeanFactory(factory);
       /* //创建bean1
        Bean1 bean1 = new Bean1();
        System.out.println("未加载后处理器："+bean1);
        postProcessor.postProcessProperties(null,bean1,"bean1");
        System.out.println("加载后处理器后："+bean1);
        //反射获取方法
        Method metadata = AutowiredAnnotationBeanPostProcessor.class.getDeclaredMethod("findAutowiringMetadata", String.class, Class.class, PropertyValues.class);
        //设置私有也能正常访问
        metadata.setAccessible(Boolean.TRUE);
        InjectionMetadata injectionMetadata = (InjectionMetadata) metadata.invoke(postProcessor, "bean1", Bean1.class, null);
        //调用 InjectionMetaData 来进行依赖注入，注入时按类型查找值
        injectionMetadata.inject(bean1, "bean1", null);
        System.out.println("模拟InjectionMetadata获取bean1："+bean1);*/
        //按照类型查找值
        //1、查找成员变量
        Field bean3 = Bean1.class.getDeclaredField("bean3");
        //required是否一定要查找到当前成员变量，true时必须查找到否则报异常，false则非必须
        DependencyDescriptor dd = new DependencyDescriptor(bean3,false);
        Object o = factory.doResolveDependency(dd, null, null, null);
        System.out.println("查找成员变量："+o);
        //2、查找方法中的成员变量
        Method setBean2 = Bean1.class.getDeclaredMethod("setBean2", Bean2.class);
        DependencyDescriptor dd2 = new DependencyDescriptor(new MethodParameter(setBean2,0),false);
        Object o2 = factory.doResolveDependency(dd2, null, null, null);
        System.out.println("查找方法中的成员变量："+o2);

    }
}
