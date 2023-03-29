package org.springframework.aop.framework.autoproxy;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

/**
 * @author jiangjs
 * @date 2023-03-30 6:17
 * @description advisor使用
 */
@Slf4j
public class SpringAdvisor {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("target1",Target1.class);
        context.registerBean("config",Config.class);
        context.registerBean(ConfigurationClassPostProcessor.class);
        //AnnotationAwareAspectJAutoProxyCreator作用：
        //1、找到所有切面，包括高级的Aspect和低级的Advisor,并将高级切面（Aspect）转换成低级切面(Advisor)
        //2、为所有的切面创建代理类
        context.registerBean(AnnotationAwareAspectJAutoProxyCreator.class);
        context.refresh();
        /*for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            log.info("定义的类名：" + beanDefinitionName);
        }*/
        AnnotationAwareAspectJAutoProxyCreator creator = context.getBean(AnnotationAwareAspectJAutoProxyCreator.class);
       /* List<Advisor> advisors = creator.findEligibleAdvisors(Target1.class, "target1");
        for (Advisor advisor : advisors) {
            log.info("低级切面："+advisor);
        }*/
        Object o1 = creator.wrapIfNecessary(new Target1(), "target1", "target1");
        ((Target1) o1).foo();

        context.close();
    }

    static class Target1 {
        public void foo() {
            System.out.println("target1 foo");
        }
    }

    @Configuration
    static class Config{
        @Bean
        public Advisor advisor(MethodInterceptor methodInterceptor){
            //创建一个切入点
            AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
            pointcut.setExpression("execution(* foo())");
            return new DefaultPointcutAdvisor(pointcut,methodInterceptor);
        }

        /**
         * 创建一个Advice
         * @return
         */
        @Bean
        public MethodInterceptor methodInterceptor(){
            return invocation -> {
                log.info("methodInterceptor before。。。。。。");
                Object proceed = invocation.proceed();
                log.info("methodInterceptor after。。。。。。");
                return proceed;
            };
        }
    }
}
