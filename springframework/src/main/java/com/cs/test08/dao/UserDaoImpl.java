package com.cs.test08.dao;

import com.cs.test08.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements IUserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insert(UserEntity userEntity) {
        String sql = "insert into user_info (username,passwd,user_id,status) values (?,?,?,?)";
        return jdbcTemplate.update(sql, userEntity.getUsername(), userEntity.getPasswd(), userEntity.getUserId(), userEntity.getStatus());
    }

    @Override
    public int delete(UserEntity userEntity) {
        String sql = "delete from user_info where user_id = ?";
        return jdbcTemplate.update(sql, userEntity.getUserId());
    }

    @Override
    public int update(UserEntity userEntity) {
        String sql = "update user_info set username=?,passwd=?,status=? where user_id = ?";
        return jdbcTemplate.update(sql, userEntity.getUsername(), userEntity.getPasswd(), userEntity.getStatus(), userEntity.getUserId());
    }

    @Override
    public UserEntity selectOne(UserEntity userEntity) {
        String sql = "select * from user_info where user_id = ?";
        // The following query finds and populates a single domain object:
        // queryForObject(String sql, RowMapper<T> rowMapper, @Nullable Object... args)
        UserEntity query = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            UserEntity entity = new UserEntity();
            entity.setUserId(rs.getString("user_id"));
            entity.setUsername(rs.getString("username"));
            entity.setPasswd(rs.getString("passwd"));
            entity.setStatus(rs.getString("status"));
            System.out.println("rowNum" + rowNum);
            return entity;
        }, userEntity.getUserId());
        return query;
    }

    @Override
    public List<UserEntity> selectList(UserEntity userEntity) {
        String sql = "select * from user_info where status = ?";

//        selectList01(userEntity, sql);

        selectList02(userEntity, sql);


        return null;
    }

    /**
     * 这个方法会查询所有的数据,
     * 将一行数据的字段名作为key，值为value存入到一个map集合中，
     * 然后将每一行数据的map作为一个元素存入到list中
     */
    private void selectList01(UserEntity userEntity, String sql) {
        List<Map<String, Object>> rowList = jdbcTemplate.queryForList(sql, userEntity.getStatus());
        rowList.forEach(row -> {
            row.keySet().forEach(columnName -> {
                System.out.print(row.get(columnName) + ", ");
            });
            System.out.println();
        });
    }

    /**
     * 通过sql查询到数据之后在rowmapper中对数据进行封装，
     * rowmapper的mapRow方法会返回一个组装好的实例，方法返回每一行数据对应的实例的集合
     */
    private void selectList02(UserEntity userEntity, String sql) {
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
        }, userEntity.getStatus());
        list.forEach(System.out::println);

    }


}
