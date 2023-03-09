package com.jiashn.bean;

import com.jiashn.bean.init.BeanDestroy;
import com.jiashn.bean.init.BeanInit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BeanApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(BeanApplication.class, args);
        run.close();
    }

    /*@Bean(initMethod = "initMethod")
    public BeanInit beanInit(){
        return new BeanInit();
    }*/

    @Bean(destroyMethod = "destroyMethod")
    public BeanDestroy beanDestroy(){
        return new BeanDestroy();
    }

}
