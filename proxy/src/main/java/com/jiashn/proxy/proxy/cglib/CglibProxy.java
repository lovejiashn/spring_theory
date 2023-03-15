package com.jiashn.proxy.proxy.cglib;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author jiangjs
 * @date 2023-03-15 6:15
 * @description cglib动态代理 原理
 */
@Slf4j
public class CglibProxy {

    static class Target{
        public void foo(){
            log.info("target foo ......");
        }
    }
    public static void main(String[] args) {
        Target target = new Target();
        //o:表示代理类自己， method:代理类中执行的方法  objects:表示传递的参数  methodProxy:避免反射进行调用方法
        Target targetProxy = (Target) Enhancer.create(Target.class, (MethodInterceptor) (o, method, objects, methodProxy) -> {
            log.info("cglib动态代理,before......");
            //Object result = method.invoke(target, objects);
            //内部没有反射，使用目标类
           // Object result = methodProxy.invoke(target,objects);
            // 内部没有反射，使用代理类
            Object result = methodProxy.invokeSuper(o, objects);
            log.info("cglib动态代理,after......");
            return result;
        });
        targetProxy.foo();
    }
}
