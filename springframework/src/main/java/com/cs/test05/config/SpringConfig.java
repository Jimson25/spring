package com.cs.test05.config;

import com.cs.test05.service.IUserService;
import com.cs.test05.service.UserServiceImpl1;
import com.cs.test05.service.UserServiceImpl2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackages = "com.cs.test05.*")
public class SpringConfig {
    @Bean
//    @Primary
    public IUserService userService1(){
        return new UserServiceImpl1();
    }

    @Bean
    public IUserService userService2(){
        return new UserServiceImpl2();
    }



}
