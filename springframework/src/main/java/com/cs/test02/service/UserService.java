package com.cs.test02.service;

import com.cs.test02.dao.UserDao;

public class UserService {

    private UserDao userDao;

    public void doSomething(){
        userDao.doSomething();
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
