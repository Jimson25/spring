package com.cs.test02;

import com.cs.test02.entity.*;
import com.cs.test02.service.UserService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test02 {

    /**
     * 依赖注入 -- set注入测试
     */
    @Test
    public void test01() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
        User user = context.getBean("user", User.class);
        System.out.println(user);
    }

    /**
     * 依赖注入 -- 构造注入
     */
    @Test
    public void test02(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
        Dog dog = context.getBean("dog", Dog.class);
        System.out.println(dog);
    }


    /**
     * 注入属性
     */
    @Test
    public void test03(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
        // 1. 字面量
        Book book = context.getBean("book", Book.class);
        System.out.println(book);

        // 2. 外部bean
        UserService userService = context.getBean("userService", UserService.class);
        userService.doSomething();

        // 3. 内部bean注入
        Emp emp = context.getBean("emp", Emp.class);
        System.out.println(emp);

        // 4.1 级联赋值一
        Emp emp02 = context.getBean("emp02", Emp.class);
        System.out.println(emp02);

        // 4.2 级联赋值二
        Emp emp03 = context.getBean("emp03", Emp.class);
        System.out.println(emp03);

    }

    /**
     * 注入集合
     */
    @Test
    public void test04() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
        //bean内部配置集合属性
        Stu stu = context.getBean("stu", Stu.class);
        System.out.println(stu);

        // 通过util命名空间引用list
        Stu stu2 = context.getBean("stu2", Stu.class);
        System.out.println(stu2.getList());
    }

    @Test
    public void test05() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
        Student student = context.getBean("student", Student.class);
        System.out.println(student);
    }
}
