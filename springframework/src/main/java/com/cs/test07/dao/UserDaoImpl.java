package com.cs.test07.dao;

import com.cs.test07.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserDaoImpl implements IUserDao {
    @Override
    public void add(UserEntity entity) {
        System.out.println(entity.toString());
    }

    @Override
    public void delete(UserEntity entity) {
        int a = 1 / 0;
    }


}
