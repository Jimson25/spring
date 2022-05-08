package com.cs.test06;

import com.cs.test06.dao.IUserDao;
import com.cs.test06.dao.UserDaoImpl;
import com.cs.test06.proxy.UserDaoImplProxy;
import org.junit.Test;

import java.lang.reflect.Proxy;

public class Test06 {

    @Test
    public void test01() {
        UserDaoImpl userDao = new UserDaoImpl();
        IUserDao instance = (IUserDao) Proxy.newProxyInstance(
                Test06.class.getClassLoader(),
                new Class[]{IUserDao.class},
                new UserDaoImplProxy(userDao));
        instance.update("aaa");

        instance.add(10, 20);

    }

}


