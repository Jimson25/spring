package com.cs.test08.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ComponentScan(basePackages = "com.cs.test08.*")
public class SpringConfig {

    /**
     * 使用注解配置jdbc连接
     */
    @Bean
    public JdbcTemplate jdbcTemplate() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/springtest");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        // 这里可以设置jdbc的一些属性，如超时时间等
        jdbcTemplate.setQueryTimeout(30);
        return jdbcTemplate;
    }
}
