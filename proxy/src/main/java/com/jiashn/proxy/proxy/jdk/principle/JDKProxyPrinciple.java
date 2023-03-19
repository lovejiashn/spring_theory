package com.jiashn.proxy.proxy.jdk.principle;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * @author jiangjs
 * @date 2023-03-16 6:22
 * @description jdk动态代理原理
 */
@Slf4j
public class JDKProxyPrinciple {

    interface Foo{
        void foo();
        int bar();
    }

    static class Target implements Foo{

        @Override
        public void foo() {
            log.info("实现目标：Target foo......");
        }

        @Override
        public int bar() {
            log.info("实现目标：Target bar......");
            return 100;
        }
    }

    interface InvocationHandler{
        Object invoke(Object p,Object[] agrs,Method method) throws Throwable;
    }
    public static void main(String[] args)  {
        Foo proxy = new $Proxy0(new InvocationHandler()  {
            @Override
            public Object invoke(Object p,Object[] agrs,Method method) throws Throwable{
                //功能增强
                log.info("功能增强，before......");
                return method.invoke(new Target(),agrs);
            }
        });
        proxy.foo();
        proxy.bar();
    }
}
