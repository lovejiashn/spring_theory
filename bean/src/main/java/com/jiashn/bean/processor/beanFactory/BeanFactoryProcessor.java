package com.jiashn.bean.processor.beanFactory;

import com.jiashn.bean.processor.beanFactory.selfdefined.ComponentScanSelfDefinedPostProcessor;
import com.jiashn.bean.processor.beanFactory.selfdefined.SelfDefinedBeanPostProcessor;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;

/**
 * @author jiangjs
 * @date 2023-03-07 6:08
 * @description BeanFactory常用后处理器
 */
public class BeanFactoryProcessor {
    public static void main(String[] args) throws IOException {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("config",Config.class);
       /* context.registerBean(ConfigurationClassPostProcessor.class); //解析@CompentScan @Bean @Import @ImportResource
        context.registerBean(MapperScannerConfigurer.class,definition ->{ //mybatis提供用于解析@MapperScanner
            //指定需要扫描的mapper路径
            definition.getPropertyValues().add("basePackage","org.apache.ibatis.annotations.Mapper");
        });*/
        //通过AnnotationUtils获取某个类下的ComponentScan类型的组件
        context.registerBean(ComponentScanSelfDefinedPostProcessor.class);
        //实现@Bean类注入
       /* CachingMetadataReaderFactory readerFactory = new CachingMetadataReaderFactory();
        MetadataReader reader = readerFactory.getMetadataReader(new ClassPathResource("com/jiashn/bean/processor/beanFactory/Config.class"));
        Set<MethodMetadata> annotatedMethods = reader.getAnnotationMetadata().getAnnotatedMethods(Bean.class.getName());
        for (MethodMetadata annotatedMethod : annotatedMethods) {
            String initMethod = annotatedMethod.getAnnotationAttributes(Bean.class.getName()).get("initMethod").toString();
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition();
            //指定方法名、类名
            builder.setFactoryMethodOnBean(annotatedMethod.getMethodName(),"config");
            builder.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);
            if (!StringUtils.isEmpty(initMethod)){
                builder.setInitMethodName(initMethod);
            }
            AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
            context.getDefaultListableBeanFactory().registerBeanDefinition(annotatedMethod.getMethodName(),beanDefinition);
        }*/
        context.registerBean(SelfDefinedBeanPostProcessor.class);
        //初始化容器
        context.refresh();
        //打印注册的bean
        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }
        //销毁容器
        context.close();
    }
}
