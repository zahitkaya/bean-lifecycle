package com.example.beanlifecycle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@SpringBootApplication
public class BeenLifecycleApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        Beans beans=ctx.getBean(Beans.class);
        beans.setBeanName("Changed Bean Name");
        ctx.close(); //Custom Destruction

    }

}
