package com.cs.test08.dao;

import com.cs.test08.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements IUserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insert(UserEntity userEntity) {
        String sql = "insert into user_info (username,passwd,user_id,status) values (?,?,?,?)";
        return jdbcTemplate.update(sql,userEntity.getUsername(),userEntity.getPasswd(),userEntity.getUserId(),userEntity.getStatus());
    }

    @Override
    public int delete(UserEntity userEntity) {
        String sql = "delete from user_info where user_id = ?";
        return jdbcTemplate.update(sql,userEntity.getUserId());
    }

    @Override
    public int update(UserEntity userEntity) {
        String sql = "update user_info set username=?,passwd=?,status=? where user_id = ?";
        return jdbcTemplate.update(sql, userEntity.getUsername(),userEntity.getPasswd(),userEntity.getStatus(),userEntity.getUserId());
    }

    @Override
    public UserEntity selectOne(UserEntity userEntity) {
        return null;
    }

    @Override
    public List<UserEntity> selectList(UserEntity userEntity) {
        return null;
    }
}
