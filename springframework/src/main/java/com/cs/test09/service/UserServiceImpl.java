package com.cs.test09.service;

import com.cs.test09.dao.IUserDao;
import com.cs.test09.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;

    @Override
//    @Transactional
    public int insert(UserEntity userEntity) throws Exception {
        return userDao.insert(userEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert2(UserEntity userEntity) throws Exception {
        return userDao.insert2(userEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(UserEntity userEntity) throws Exception {
        int delete = userDao.delete(userEntity);
        if (delete == 1) {
            throw new Exception();
        }
        return delete;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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
