package com.cs.test10;

import com.cs.test10.config.SpringConfig;
import com.cs.test10.dao.IUserDao;
import com.cs.test10.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class Test10 {
    @Autowired
    private IUserDao userDao;

    @Test
    public void test01() {
        GenericApplicationContext context = new GenericApplicationContext();
        context.refresh();
        context.registerBean("user", UserEntity.class, UserEntity::new);
        UserEntity user = context.getBean("user", UserEntity.class);
    }

    @Test
    public void test02(){
        userDao.insert();
    }


}
