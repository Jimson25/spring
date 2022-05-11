package com.cs.test09;

import com.cs.test09.config.SpringConfig;
import com.cs.test09.dao.IUserDao;
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


}
