package com.jiashn.bean.processor.beanFactory.selfdefined;

import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.util.StringUtils;

import java.util.Set;

/**
 * @author jiangjs
 * @date 2023-03-09 5:41
 * @description
 */
public class BeanSelfDefinedPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @SneakyThrows
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        //获取CachingMetadataReaderFactory,用于读取元数据信息
        CachingMetadataReaderFactory readerFactory = new CachingMetadataReaderFactory();
        MetadataReader reader = readerFactory.getMetadataReader(new ClassPathResource("com/jiashn/bean/processor/beanFactory/Config.class"));
        //获取@Bean注解的方法
        Set<MethodMetadata> methods = reader.getAnnotationMetadata().getAnnotatedMethods(Bean.class.getName());
        for (MethodMetadata method : methods) {
            //Bean的定义
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition();
            //设置读取方法参数类型
            builder.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);
            //设置InitMethod
            String initMethod = method.getAnnotationAttributes(Bean.class.getName()).get("initMethod").toString();
            if (!StringUtils.isEmpty(initMethod)){
                builder.setInitMethodName(initMethod);
            }
            //设置工厂方法
            String methodName = method.getMethodName();
            builder.setFactoryMethodOnBean(methodName,"config");
            //加入BeanFactory
            registry.registerBeanDefinition(methodName,builder.getBeanDefinition());
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
