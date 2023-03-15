package com.jiashn.proxy.proxy.jdk;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Proxy;

/**
 * @author jiangjs
 * @date 2023-03-15 6:14
 * @description 实现JDK的动态代理
 */
@Slf4j
public class JDKProxy {
    interface Foo{
        void foo();
    };
    static final class Target implements Foo{

        @Override
        public void foo() {
            log.info("foo method......");
        }
    }
    public static void main(String[] args) {
        Target target = new Target();
        //用来加载在运行期间动态生成的字节码
        ClassLoader classLoader = JDKProxy.class.getClassLoader();
        Foo o = (Foo)Proxy.newProxyInstance(classLoader, new Class[]{Foo.class}, (p, method, args1) -> {
            log.info("实现调用方法前增强......");
            Object result = method.invoke(target, args1);
            log.info("实现调用方法后增强......");
            return result;
        });
        o.foo();
    }
}
