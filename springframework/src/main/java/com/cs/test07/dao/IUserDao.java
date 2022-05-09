package com.cs.test07.dao;

import com.cs.test07.entity.UserEntity;

public interface IUserDao {
    public void add(UserEntity entity);

    void delete(UserEntity entity);
}
