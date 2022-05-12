package com.cs.test09;

import com.cs.test09.config.SpringConfig;
import com.cs.test09.entity.UserEntity;
import com.cs.test09.service.IUserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test09 {
    @Test
    public void test01() {
        UserEntity entity = new UserEntity();
        entity.setUserId("10002");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        IUserService userServiceImpl = context.getBean("userServiceImpl", IUserService.class);
        try {
            userServiceImpl.delete(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test02() {
        UserEntity entity = new UserEntity();
        entity.setUserId("20001");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        IUserService userServiceImpl = context.getBean("userServiceImpl", IUserService.class);
        try {
            userServiceImpl.insert(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test03() {
        UserEntity entity = new UserEntity();
        entity.setUserId("20001");
        entity.setUsername("testUpdate");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        IUserService userServiceImpl = context.getBean("userServiceImpl", IUserService.class);
        try {
            // org.springframework.transaction.IllegalTransactionStateException:
            // No existing transaction found for transaction marked with propagation 'mandatory'
            userServiceImpl.update(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test04() throws Exception {
        UserEntity entity = new UserEntity();
        entity.setUserId("20001");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        IUserService userServiceImpl = context.getBean("userServiceImpl", IUserService.class);
        userServiceImpl.delete(entity);
    }


    @Test
    public void test05() throws Exception {
        UserEntity entity = new UserEntity();
        entity.setUserId("20002");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        IUserService userServiceImpl = context.getBean("userServiceImpl", IUserService.class);
        try {
            userServiceImpl.insert2(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}
