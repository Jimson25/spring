package com.cs.test07;

import com.cs.test07.config.SpringConfig;
import com.cs.test07.dao.IUserDao;
import com.cs.test07.entity.UserEntity;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class test07 {
    @Test
    public void test01(){
        UserEntity entity = new UserEntity();
        entity.setName("zhangSan");
        entity.setAge(18);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        IUserDao userDao = context.getBean("userDaoImpl", IUserDao.class);
//        userDao.add(entity);
        userDao.delete(entity);

    }
}
