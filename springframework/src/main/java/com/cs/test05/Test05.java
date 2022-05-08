package com.cs.test05;

import com.cs.test05.config.SpringConfig;
import com.cs.test05.controller.UserController;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test05 {

    @Test
    public void test01(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserController userController = context.getBean("userController", UserController.class);
        userController.print();

//        userController.doList();
//        userController.doMap();
    }


}
