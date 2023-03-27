package com.jiashn.proxy.proxy.jdk.principle;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author jiangjs
 * @date 2023-03-20 6:21
 * @description 测试jdk代理反射
 */
@Slf4j
public class TestJdkProxy {

    public static void main(String[] args) throws Exception {
        Method foo = TestJdkProxy.class.getDeclaredMethod("foo",int.class);
        for (int i =1;i<=17;i++){
            show(i,foo);
            foo.invoke(null,i);
        };
        System.in.read();
    }

    //方法反射调用时，底层MethodAccessor的实现类
    private static void show(int i, Method method) throws NoSuchMethodException, IllegalAccessException, ClassNotFoundException, NoSuchFieldException, InvocationTargetException {
        Method getMethodAccessor = Method.class.getDeclaredMethod("getMethodAccessor");
        getMethodAccessor.setAccessible(true);
        Object invoke = getMethodAccessor.invoke(method);
        if (invoke == null) {
            return;
        }
        Field delegate = Class.forName("jdk.internal.reflect.DelegatingMethodAccessorImpl").getDeclaredField("delegate");
        delegate.setAccessible(true);
        log.info("调用次数："+i+"   方法："+delegate.get(invoke));
    }

    public static void foo(int i){
        log.info(i + "：foo");
    }
}
