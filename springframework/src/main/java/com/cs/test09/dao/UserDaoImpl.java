package com.cs.test09.dao;

import com.cs.test09.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements IUserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public int insert(UserEntity userEntity) throws Exception {
        String sql = "insert into user_info (username,passwd,user_id,status) values (?,?,?,?)";
        int update = jdbcTemplate.update(sql, userEntity.getUsername(), userEntity.getPasswd(), userEntity.getUserId(), userEntity.getStatus());
        if (update == 1) {
            throw new Exception();
        }
        return -1;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
    public int insert2(UserEntity userEntity) throws Exception {
        String sql = "insert into user_info (username,passwd,user_id,status) values (?,?,?,?)";
        int update = jdbcTemplate.update(sql, userEntity.getUsername(), userEntity.getPasswd(), userEntity.getUserId(), userEntity.getStatus());
        if (update == 1) {
            throw new Exception();
        }
        return -1;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int delete(UserEntity userEntity) throws Exception {
        String sql = "delete from user_info where user_id = ?";
        int update = jdbcTemplate.update(sql, userEntity.getUserId());
        if (update == 1){
            throw new Exception();
        }
        return -1;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY, rollbackFor = Exception.class)
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

    @Override
    public void batchExec(UserEntity entity) {
        batchInsert();
    }

    private void batchInsert() {
        String sql = "insert into user_info(user_id, username, passwd, status) values(?,?,?,?)";
        List<Object[]> paraList = new ArrayList<>();
        paraList.add(new String[]{"10001", "zhao", "1", "1"});
        paraList.add(new String[]{"10002", "qian", "1", "1"});
        paraList.add(new String[]{"10003", "sun", "1", "1"});
        paraList.add(new String[]{"10004", "li", "1", "1"});
        int[] nums = jdbcTemplate.batchUpdate(sql, paraList);
        System.out.println(Arrays.toString(nums));
    }


    /**
     * ????????????????????????????????????,
     * ?????????????????????????????????key?????????value???????????????map????????????
     * ???????????????????????????map???????????????????????????list???
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
     * ??????sql????????????????????????rowmapper???????????????????????????
     * rowmapper???mapRow?????????????????????????????????????????????????????????????????????????????????????????????
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
