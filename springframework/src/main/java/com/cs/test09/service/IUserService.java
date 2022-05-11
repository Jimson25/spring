package com.cs.test09.service;

import com.cs.test09.entity.UserEntity;

import java.util.List;

public interface IUserService {
    int insert(UserEntity userEntity);

    int delete(UserEntity userEntity) throws Exception;

    int update(UserEntity userEntity);

    UserEntity selectOne(UserEntity userEntity);

    List<UserEntity> selectList(UserEntity userEntity);

}
