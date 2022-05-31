package com.cs.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@ToString
@Component
@ConfigurationProperties(prefix = "car")
public class Car {

    /**
     * name of car
     */
    private String name;
    /**
     * price of car
     */
    private String price;

}
