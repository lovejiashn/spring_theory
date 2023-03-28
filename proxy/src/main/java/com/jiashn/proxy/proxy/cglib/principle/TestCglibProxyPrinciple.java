package com.jiashn.proxy.proxy.cglib.principle;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.cglib.proxy.Proxy;

import java.lang.reflect.Method;

/**
 * @author jiangjs
 * @date 2023-03-28 6:37
 * @description
 */
@Slf4j
public class TestCglibProxyPrinciple {

    public static void main(String[] args) {
        CglibProxyPrinciple principle = new CglibProxyPrinciple();
        Target target = new Target();
        principle.setMethodInterceptor(new MethodInterceptor() {
            @Override
            public Object intercept(Object p, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                log.info("增强,,,,,,");
               // return methodProxy.invoke(target,args);
                return methodProxy.invokeSuper(p,args);
            }
        });
        principle.save();
        principle.save(100);
        principle.save(200L);

    }
}
