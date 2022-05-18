package com.cs;

import com.cs.entity.Pet;
import com.cs.entity.UserEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@SpringBootApplication
public class Boot01HelloworldApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Boot01HelloworldApplication.class, args);
        UserEntity user1 = context.getBean("user", UserEntity.class);
        Pet pet = context.getBean("pet", Pet.class);
        System.out.println(user1.getPet() == pet);

        System.out.println("=========================");
        String[] beanNamesForType = context.getBeanNamesForType(UserEntity.class);

        Arrays.stream(beanNamesForType).forEach(System.out::println);
    }

}
