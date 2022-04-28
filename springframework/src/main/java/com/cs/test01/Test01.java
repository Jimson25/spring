package com.cs.test01;

import com.cs.test01.entity.User;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test01 {

    @Test
    public void testAdd(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        User user = (User) context.getBean("user");
        System.out.println(user);
        user.doSomething();

    }
}
