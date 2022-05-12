package com.cs.test10;

import com.cs.test10.config.SpringConfig;
import com.cs.test10.dao.IUserDao;
import com.cs.test10.dao.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@Deprecated
@SpringJUnitConfig(SpringConfig.class)
public class test4J5 {
    @Autowired
    private IUserDao userDao;

    public void test01() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserDaoImpl dao = context.getBean("userDaoImpl", UserDaoImpl.class);
        dao.insert();
        userDao.insert();
    }


}
