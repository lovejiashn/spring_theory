package com.jiashn.springtheory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiangjs
 * @date 2023-03-01 5:58
 * @description 实现模板方法
 */
public class TestMethodTemplate {
    public static void main(String[] args) {
        MyBeanFactory beanFactory = new MyBeanFactory();
        beanFactory.addBeanPostProcessor(bean -> System.out.println(">>>>>注入@Autowired"));
        beanFactory.addBeanPostProcessor(bean -> System.out.println(">>>>>注入@Resource"));
        beanFactory.getBean();
    }

    static class MyBeanFactory{
        public Object getBean(){
            Object bean = new Object();
            System.out.println(">>>>>>>>实例化");
            System.out.println(">>>>>>>>依赖注入");
            System.out.println(">>>>>>>>初始化");
            for (BeanPostProcessor processor : processors) {
                processor.inject(bean);
            }
            System.out.println(">>>>>>>>销毁");
            return bean;
        }

        List<BeanPostProcessor> processors = new ArrayList<>();
        public void addBeanPostProcessor(BeanPostProcessor processor){
            processors.add(processor);
        }
    }

    interface BeanPostProcessor{
        void inject(Object bean);
    }
}
