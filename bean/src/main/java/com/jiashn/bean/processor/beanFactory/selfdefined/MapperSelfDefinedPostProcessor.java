package com.jiashn.bean.processor.beanFactory.selfdefined;

import lombok.SneakyThrows;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;

/**
 * @author jiangjs
 * @date 2023-03-09 6:15
 * @description Mapper注入到BeanFactory
 */
public class MapperSelfDefinedPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @SneakyThrows
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        //根据路径获取资源
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:com/jiashn/bean/processor/beanFactory/mapper/**/*.class");
        CachingMetadataReaderFactory readerFactory = new CachingMetadataReaderFactory();
        AnnotationBeanNameGenerator generator = new AnnotationBeanNameGenerator();
        for (Resource resource : resources) {
            //读取资源的元信息
            MetadataReader reader = readerFactory.getMetadataReader(resource);
            //获取类的元信息，用于判断当前类是否为接口
            ClassMetadata metadata = reader.getClassMetadata();
            if (metadata.isInterface()) {
                //Bean的定义
                AbstractBeanDefinition definition = BeanDefinitionBuilder.genericBeanDefinition(MapperFactoryBean.class)
                        //添加参数值
                        .addConstructorArgValue(metadata.getClassName())
                        //设置自动装配的类型
                        .setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE)
                        .getBeanDefinition();
                //获取类名称
                AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(metadata.getClassName())
                        .getBeanDefinition();
                String beanName = generator.generateBeanName(beanDefinition, registry);
                registry.registerBeanDefinition(beanName,definition);
            }
        }


    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
