package com.cs.test03.entity;

import org.springframework.beans.factory.FactoryBean;

/**
 * 定义一个类实现FactoryBean接口
 * 当使用配置文件配置当前类型的bean时，在使用IOC获取时会调用实现类的getObject方法并获取其返回值作为bean实例返回
 */
public class MyBean implements FactoryBean<Course> {
    @Override
    public Course getObject() throws Exception {
        Course course = new Course();
        course.setName("java");
        return course;
    }

    @Override
    public Class<?> getObjectType() {
        return Course.class;
    }

    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }
}
