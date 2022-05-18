package com.cs.controller;

import com.cs.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private Car car;

    @RequestMapping("/hello")
    public String handler(){
        return "hello spring-boot";
    }

    @RequestMapping("/car")
    public String car(){
        return car.toString();
    }
}
