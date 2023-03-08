package com.jiashn.bean.processor.beanFactory.selfdefined;

import com.jiashn.bean.processor.beanFactory.Config;
import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author jiangjs
 * @date 2023-03-08 5:53
 * @description 自定义后处理器,实现ComponentScan扫描注入BeanFactory
 */
public class ComponentScanSelfDefinedPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @SneakyThrows
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        ComponentScan componentScan = AnnotationUtils.findAnnotation(Config.class, ComponentScan.class);
        if (Objects.nonNull(componentScan)){
            CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();
            String[] basePackages = componentScan.basePackages();
            AnnotationBeanNameGenerator generator = new AnnotationBeanNameGenerator();
            for (String basePackage : basePackages) {
                //将basePackage转换成classpath*:形式
                String classPath = "classpath*:"+basePackage.replace(".","/")+"/**/*.class";
                PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
                for (Resource resource : resolver.getResources(classPath)) {
                    //获取资源的元信息
                    MetadataReader reader = factory.getMetadataReader(resource);
                    String className = reader.getClassMetadata().getClassName();
                    boolean annotation = reader.getAnnotationMetadata().hasAnnotation(Component.class.getName());
                    boolean metaAnnotation = reader.getAnnotationMetadata().hasMetaAnnotation(Component.class.getName());
                    if (annotation || metaAnnotation){
                        //定义bean
                        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(className).getBeanDefinition();
                        String name = generator.generateBeanName(beanDefinition, registry);
                        //将bean注入BeanFactory
                        registry.registerBeanDefinition(name,beanDefinition);
                    }
                }
            }
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    }
}
