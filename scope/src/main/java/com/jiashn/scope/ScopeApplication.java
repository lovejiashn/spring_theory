package com.jiashn.scope;

import com.jiashn.scope.lapse.FirstClass;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ScopeApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ScopeApplication.class, args);
        FirstClass bean = context.getBean(FirstClass.class);
        System.out.println("......代理类：" + bean.printBean().getClass());
        System.out.println("......输出Bean：" +  bean.printBean());
        System.out.println("......代理类：" + bean.printBean().getClass());
        System.out.println("......输出Bean：" +  bean.printBean());
        System.out.println("......代理类：" + bean.printBean().getClass());
        System.out.println("......输出Bean：" +  bean.printBean());
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("......代理类getMethodBean：" + bean.getMethodBean().getClass());
        System.out.println("......输出getMethodBean：" +  bean.getMethodBean());
        System.out.println("......代理类getMethodBean：" + bean.getMethodBean().getClass());
        System.out.println("......输出getMethodBean：" +  bean.getMethodBean());
        System.out.println("......代理类getMethodBean：" + bean.getMethodBean().getClass());
        System.out.println("......输出getMethodBean：" +  bean.getMethodBean());

        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("......输出getFactory：" +  bean.getFactory());
        System.out.println("......输出getFactory：" +  bean.getFactory());

        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("......输出getContext：" +  bean.getContext());
        System.out.println("......输出getContext：" +  bean.getContext());
        context.close();
    }

}
