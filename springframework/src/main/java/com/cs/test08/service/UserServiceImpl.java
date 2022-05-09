package com.cs.test08.service;

import com.cs.test08.dao.IUserDao;
import com.cs.test08.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    private IUserDao userDao;

    @Override
    public int insert(UserEntity userEntity) {
        return userDao.insert(userEntity);
    }

    @Override
    public int delete(UserEntity userEntity) {
        return userDao.delete(userEntity);
    }

    @Override
    public int update(UserEntity userEntity) {
        return userDao.update(userEntity);
    }

    @Override
    public UserEntity selectOne(UserEntity userEntity) {
        return userDao.selectOne(userEntity);
    }

    @Override
    public List<UserEntity> selectList(UserEntity userEntity) {
        return userDao.selectList(userEntity);
    }
}
