package com.jiashn.proxy.proxy.cglib.principle;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

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
    static MethodProxy saveProxy0;
    static MethodProxy saveProxy1;
    static MethodProxy saveProxy2;
    static {
        try {
            save0 = Target.class.getMethod("save");
            save1 = Target.class.getMethod("save",int.class);
            save2 = Target.class.getMethod("save",long.class);
            //参数1：目标类，参数2：代理类，参数3：描述，即描述参数类型及返回值,()V表示无参，无返回值，参数4：目标方法，参数5：原始方法
            saveProxy0 = MethodProxy.create(Target.class,CglibProxyPrinciple.class,"()V","save","saveSuper");
            saveProxy1 = MethodProxy.create(Target.class,CglibProxyPrinciple.class,"(I)V","save","saveSuper");
            saveProxy2 = MethodProxy.create(Target.class,CglibProxyPrinciple.class,"(J)V","save","saveSuper");
        } catch (NoSuchMethodException e) {
            throw new NoSuchMethodError(e.getMessage());
        }
    }

//---------------原始方法---------------
    public void saveSuper() {
        super.save();
    }
    public void saveSuper(int i) {
        super.save(i);
    }
    public void saveSuper(long j) {
        super.save(j);
    }

//---------------原始方法---------------
    @Override
    public void save() {
        try {
            methodInterceptor.intercept(this,save0,new Object[0],saveProxy0);
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }
    }

    @Override
    public void save(int i) {
        try {
            methodInterceptor.intercept(this,save1,new Object[]{i},saveProxy1);
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }
    }

    @Override
    public void save(long j) {
        try {
            methodInterceptor.intercept(this,save2,new Object[]{j},saveProxy2);
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }
    }
}
