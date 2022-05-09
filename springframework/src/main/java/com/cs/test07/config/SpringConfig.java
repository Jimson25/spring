package com.cs.test07.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
// 使用注解配置开启aspect生成代理对象
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages = "com.cs.test07.*")
public class SpringConfig {
}
