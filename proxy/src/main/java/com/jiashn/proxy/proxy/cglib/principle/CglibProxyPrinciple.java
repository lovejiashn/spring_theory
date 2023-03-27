package com.jiashn.proxy.proxy.cglib.principle;

import org.springframework.cglib.proxy.MethodInterceptor;

import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * @author jiangjs
 * @date 2023-03-28 6:14
 * @description cglib原理
 */
public class CglibProxyPrinciple extends Target {

    private MethodInterceptor methodInterceptor;

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    static Method save0;
    static Method save1;
    static Method save2;
    static {
        try {
            save0 = Target.class.getMethod("save");
            save1 = Target.class.getMethod("save",int.class);
            save2 = Target.class.getMethod("save",long.class);
        } catch (NoSuchMethodException e) {
            throw new NoSuchMethodError(e.getMessage());
        }
    }


    @Override
    public void save() {
        try {
            methodInterceptor.intercept(this,save0,new Object[0],null);
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }
    }

    @Override
    public void save(int i) {
        try {
            methodInterceptor.intercept(this,save1,new Object[]{i},null);
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }
    }

    @Override
    public void save(long j) {
        try {
            methodInterceptor.intercept(this,save2,new Object[]{j},null);
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }
    }
}
