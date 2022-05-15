package com.example.springwebflux.mapper.impl;

import com.example.springwebflux.entity.UserEntity;
import com.example.springwebflux.mapper.IUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class IUserMapperImpl implements IUserMapper {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void insert(UserEntity entity) {
        String sql = "insert into user_info (username,passwd,user_id,status) values (?,?,?,?)";
        jdbcTemplate.update(sql, entity.getUsername(), entity.getPasswd(), entity.getUserId(), entity.getStatus());

    }

    @Override
    public UserEntity selectById(String id) {
        String sql = "select * from user_info where user_id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            UserEntity entity = new UserEntity();
            entity.setUserId(rs.getString("user_id"));
            entity.setUsername(rs.getString("username"));
            entity.setPasswd(rs.getString("passwd"));
            entity.setStatus(rs.getString("status"));
            System.out.println("rowNum" + rowNum);
            return entity;
        }, id);
    }

    @Override
    public List<UserEntity> selectListByStatus(UserEntity entity) {
        String sql = "select * from user_info where status = ?";
        List<UserEntity> list = jdbcTemplate.query(sql, new RowMapper<UserEntity>() {
            @Override
            public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserEntity entity = new UserEntity();
                entity.setUserId(rs.getString("user_id"));
                entity.setUsername(rs.getString("username"));
                entity.setPasswd(rs.getString("passwd"));
                entity.setStatus(rs.getString("status"));
                return entity;
            }
        }, entity.getStatus());

        return list;
    }
}

