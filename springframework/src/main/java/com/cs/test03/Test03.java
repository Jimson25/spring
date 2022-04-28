package com.cs.test03;

import com.alibaba.druid.pool.DruidDataSource;
import com.cs.test03.entity.*;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test03 {

    /**
     * 配置bean实现FactoryBean接口，实际获取bean时返回的是其getObject方法的返回值
     */
    @Test
    public void test01() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean3.xml");
        Course myBean = context.getBean("myBean", Course.class);
        System.out.println(myBean.getName());
    }

    /**
     * spring bean作用域
     */
    @Test
    public void test02() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean3.xml");

        // 单实例 每次获取的都是同一个bean对象，spring在解析配置文件时创建对象
        Singleton singleton1 = context.getBean("singleton", Singleton.class);
        Singleton singleton2 = context.getBean("singleton", Singleton.class);
        System.out.println(singleton1 == singleton2);   // true

        // 多实例 每次获取的都是一个新的bean对象，spring在每次获取对象时创建一个新的对象
        Prototype prototype1 = context.getBean("prototype", Prototype.class);
        Prototype prototype2 = context.getBean("prototype", Prototype.class);
        System.out.println(prototype1 == prototype2);   // false
    }

    /**
     * bean生命周期 创建实例-设置属性值-初始化-销毁
     */
    @Test
    public void test03() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean3.xml");
        Orders orders = context.getBean("orders", Orders.class);
        context.close();
    }

    /**
     * bean生命周期 添加后置处理器执行过程
     * 创建一个类实现BeanPostProcessor接口并重写postProcessBeforeInitialization和postProcessAfterInitialization方法 将这个类配置为spring bean
     * 此后在每个bean实例初始化之前之后分别会执行上面对应方法
     */
    @Test
    public void test04() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean3.xml");
        context.getBean("postProcessObject", MyBeanPost.class);
        context.close();
    }

    @Test
    public void test05(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean3.xml");
        DruidDataSource dataSource = context.getBean("dataSource", DruidDataSource.class);
        System.out.println(dataSource.getUsername());
    }

}
