package com.example.springwebflux.mapper;

import com.example.springwebflux.entity.UserEntity;

import java.util.List;

public interface IUserMapper {
    void insert(UserEntity entity);

    UserEntity selectById(String id);

    List<UserEntity> selectListByStatus(UserEntity entity);
}
