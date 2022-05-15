package com.example.springwebflux.mapper.impl;

import com.example.springwebflux.entity.UserEntity;
import com.example.springwebflux.mapper.IUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class IUserMapperImpl implements IUserMapper {



    @Override
    public void insert(UserEntity entity) {
        System.out.println("IUserMapperImpl.insert");
    }

    @Override
    public UserEntity selectById(String id) {
        System.out.println("IUserMapperImpl.selectById");
        return new UserEntity(id,null,null,null);
    }

    @Override
    public List<UserEntity> selectListByStatus(UserEntity entity) {
        List<UserEntity> list = new ArrayList<>();
        list.add(new UserEntity("11111",null,null,null));
        return list;
    }
}

