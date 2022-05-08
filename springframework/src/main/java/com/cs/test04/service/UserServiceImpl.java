package com.cs.test04.service;

import com.cs.test04.entity.UserEntity;
import com.cs.test04.mapper.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 当同时存在构造方法和set方法时，先通过构造方法注入，这时候即使注入成功也会调用set方法
 */
@Service
public class UserServiceImpl implements UserService {
    // 通过字段注入
    // @Autowired
    private UserDao userDao;

    @Override
    public void insert(UserEntity user) {
        userDao.insert(user);
    }

    // 通过set方法注入，同样需要在set方法上面添加@autowired注解
    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    // 通过构造方法注入
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

}
