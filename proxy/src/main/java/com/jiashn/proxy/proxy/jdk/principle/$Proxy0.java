package com.jiashn.proxy.proxy.jdk.principle;

import lombok.extern.slf4j.Slf4j;
import com.jiashn.proxy.proxy.jdk.principle.JDKProxyPrinciple.Foo;
import com.jiashn.proxy.proxy.jdk.principle.JDKProxyPrinciple.InvocationHandler;

import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * @author jiangjs
 * @date 2023-03-16 6:22
 * @description
 */
@Slf4j
public class $Proxy0 implements Foo {

    private InvocationHandler h;
    public $Proxy0(InvocationHandler h){
        this.h = h;
    }
    @Override
    public void foo() {
        try {
            h.invoke(this,new Object[0],foo);
        }  catch (RuntimeException | Error e) {
            e.printStackTrace();
        } catch (Throwable e){
            throw new UndeclaredThrowableException(e);
        }
    }

    @Override
    public int bar() {
        try {
            int result = (int) h.invoke(this,new Object[0],bar);
            log.info("获取结果："+result);
            return result;
        }  catch (RuntimeException | Error e) {
            e.printStackTrace();
        } catch (Throwable e){
            throw new UndeclaredThrowableException(e);
        }
        return 0;
    }
    static Method foo = null;
    static Method bar = null;
    static {
        try {
            foo = Foo.class.getMethod("foo");
            bar = Foo.class.getMethod("bar");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

}
