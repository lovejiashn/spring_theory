package com.jiashn.point.pointcut;

import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author jiangjs
 * @date 2023-03-29 6:28
 * @description 切面匹配
 */
public class SpringPointCut {

    public static void main(String[] args) throws NoSuchMethodException {
       /* //实现表达式匹配
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* bar())");
        boolean foo = pointcut.matches(T1.class.getMethod("foo"), T1.class);
        boolean bar = pointcut.matches(T1.class.getMethod("bar"), T1.class);
        System.out.println("foo是否匹配："+foo);
        System.out.println("bar是否匹配："+bar);

        //实现注解匹配
        AspectJExpressionPointcut ajep = new AspectJExpressionPointcut();
        ajep.setExpression("@annotation(org.springframework.context.annotation.Bean)");
        boolean anFoo = ajep.matches(T1.class.getMethod("foo"), T1.class);
        boolean anBar = ajep.matches(T1.class.getMethod("bar"), T1.class);
        System.out.println("anFoo是否匹配："+anFoo);
        System.out.println("anBar是否匹配："+anBar);*/

        StaticMethodMatcherPointcut matcherPointcut = new StaticMethodMatcherPointcut() {
            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                //在方法上进行注解检查
                MergedAnnotations methodAnnotations = MergedAnnotations.from(method);
                //检查方法上是否有Bean注解
                if (methodAnnotations.isPresent(Bean.class)){
                    return Boolean.TRUE;
                }
                //在类上进行注解检查,
                // MergedAnnotations.SearchStrategy.TYPE_HIERARCHY表示在继承类上是否也有注解，有则返回true，否则false
                // MergedAnnotations.SearchStrategy.DIRECT：只匹配当前类上是否存在注解
                MergedAnnotations classAnnotation = MergedAnnotations.from(targetClass, MergedAnnotations.SearchStrategy.TYPE_HIERARCHY);
                //检查类上是否有Component注解
                if (classAnnotation.isPresent(Component.class)){
                    return Boolean.TRUE;
                }

                return false;
            }
        };
        boolean anFoo = matcherPointcut.matches(T1.class.getMethod("foo"), T1.class);
        boolean anBar = matcherPointcut.matches(T1.class.getMethod("bar"), T1.class);
        System.out.println("anFoo是否@Bean匹配："+anFoo);
        System.out.println("anBar是否@Bean匹配："+anBar);
        boolean t2 = matcherPointcut.matches(T2.class.getMethod("foo"), T2.class);
        System.out.println("T2是否@Component匹配："+t2);
        boolean t3 = matcherPointcut.matches(T3.class.getMethod("foo"), T3.class);
        System.out.println("T3是否@Component匹配："+t3);
    }

    static class T1 {
        @Bean
        public void foo() {
        }
        public void bar() {
        }
    }

    @Component
    static class T2 {
        public void foo() {
        }
    }

    @Component
    interface I3 {
        void foo();
    }
    static class T3 implements I3 {
        @Override
        public void foo() {
        }
    }
}
