package com.cs.test04.mapper;

import com.cs.test04.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
//@Component("UserDaoImpl")
// 如果没有指定组件名，则默认使用首字母小写的类名
public class UserDaoImpl implements UserDao {
    public void insert(UserEntity user) {
        System.out.println(user.toString());
    }
}
