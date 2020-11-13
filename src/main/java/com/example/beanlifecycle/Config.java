package com.example.beanlifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean(initMethod = "initBean",destroyMethod = "destroyBean")
    public Beans bean(){
        return new Beans();
    }
    @Bean
    public User user(){
        return new User();
    }

}
