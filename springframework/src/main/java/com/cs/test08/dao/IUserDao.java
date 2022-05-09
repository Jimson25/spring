package com.cs.test08.dao;

import com.cs.test08.entity.UserEntity;

import java.util.List;

public interface IUserDao {
    int insert(UserEntity userEntity);

    int delete(UserEntity userEntity);

    int update(UserEntity userEntity);

    UserEntity selectOne(UserEntity userEntity);

    List<UserEntity> selectList(UserEntity userEntity);

}
