package com.cs.config;

import com.cs.entity.Car;
import com.cs.entity.Pet;
import com.cs.entity.UserEntity;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


/**
 * proxyBeanMethods:是否代理bean中的方法，默认值为true。当设置为true时，从容器中获取SpringConfig的bean实例时获取的是代理对象
 * true：full模式，在此模式下每次获取bean实例都需要从容器中查找是否存在，如果存在就返回，保证bean都是单实例的
 * false：lite模式，每次获取bean实例都是返回一个全新的实例
 *
 *  这里主要是针对bean依赖，如下面代码中，user依赖pet，
 *  这时候如果设置为true，则在创建初始化user实例时会从容器中获取pet实例，
 *  而如果设置的是false，初始化时会创建一个新的pet实例
 */
@Configuration(proxyBeanMethods = true)
/**
 * 用于导入一个组件到容器中
 */
@Import({UserEntity.class})
@EnableConfigurationProperties(Car.class)
public class SpringConfig {

    @Bean
    public UserEntity user() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("zhangsan");
        userEntity.setPet(pet());
        return userEntity;
    }

    @Bean
    public Pet pet() {
        return new Pet();
    }
}
