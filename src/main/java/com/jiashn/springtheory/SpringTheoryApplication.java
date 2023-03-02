package com.jiashn.springtheory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Locale;

/**
 * @author jiangjs
 */
@SpringBootApplication
public class SpringTheoryApplication {

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(SpringTheoryApplication.class, args);
        /*System.out.println(context.getMessage("hi",null, Locale.JAPAN));
        System.out.println(context.getMessage("hi",null, Locale.CHINA));
        System.out.println(context.getMessage("hi",null, Locale.ENGLISH));

        Resource[] resources = context.getResources("classpath:message*.properties");
        for (Resource resource : resources) {
            System.out.println("获取资源："+resource);
        }

        Resource[] facResources = context.getResources("classpath*:META-INF/spring.factories");
        for (Resource resource : facResources) {
            System.out.println("获取fac资源："+resource);
        }
        String java_home = context.getEnvironment().getProperty("java_home");
        String port = context.getEnvironment().getProperty("server.port");
        System.out.println("java_home："+java_home);
        System.out.println("port："+port);*/
        context.close();
    }

}
