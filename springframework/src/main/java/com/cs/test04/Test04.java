package com.cs.test04;

import com.cs.test04.config.SpringConfig;
import com.cs.test04.entity.UserEntity;
import com.cs.test04.service.UserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test04 {

    @Test
    public void test01() {

        UserEntity userEntity = new UserEntity();
        userEntity.setName("zhangSan");
        userEntity.setAge("18");


        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
//        UserDao userDaoImpl = context.getBean("userDaoImpl", UserDao.class);
//        userDaoImpl.insert(userEntity);

        UserService userService = context.getBean("userServiceImpl", UserService.class);
        userService.insert(userEntity);
    }
}
