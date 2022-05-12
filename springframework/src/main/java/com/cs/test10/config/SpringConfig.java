package com.cs.test10.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.cs.test10.dao")
public class SpringConfig {
}
