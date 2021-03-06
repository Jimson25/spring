package com.cs.test08;

import com.cs.test08.common.ConstField;
import com.cs.test08.config.SpringConfig;
import com.cs.test08.dao.IUserDao;
import com.cs.test08.entity.UserEntity;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class Test08 {
    @Test
    public void test01() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        JdbcTemplate jdbcTemplate = context.getBean("jdbcTemplate", JdbcTemplate.class);
        Integer integer = jdbcTemplate.queryForObject("select count(*) from user_info where user_id = ? ", Integer.class, "00001");
        System.out.println(integer);
        System.out.println(jdbcTemplate.getQueryTimeout());
    }

    @Test
    public void testInsert() {
        UserEntity entity = new UserEntity();
        entity.setUsername("zhangSan");
        entity.setPasswd("12345");
        entity.setUserId("00001");
        entity.setStatus(ConstField.USER_STATUS_ENABLE);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        IUserDao dao = context.getBean("userDaoImpl", IUserDao.class);
        dao.insert(entity);
    }

    @Test
    public void testUpdate() {
        UserEntity entity = new UserEntity();
        entity.setStatus(ConstField.USER_STATUS_DISABLE);
        entity.setUserId("00001");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        IUserDao dao = context.getBean("userDaoImpl", IUserDao.class);
        dao.update(entity);
    }

    @Test
    public void testDelete() {
        UserEntity entity = new UserEntity();
        entity.setUserId("00001");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        IUserDao dao = context.getBean("userDaoImpl", IUserDao.class);
        dao.delete(entity);
    }

    @Test
    public void testSelectOne() {
        UserEntity entity = new UserEntity();
        entity.setUserId("00001");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        IUserDao dao = context.getBean("userDaoImpl", IUserDao.class);
        System.out.println(dao.selectOne(entity));
    }

    @Test
    public void testSelectList() {
        UserEntity entity = new UserEntity();
        entity.setStatus("1");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        IUserDao dao = context.getBean("userDaoImpl", IUserDao.class);
        dao.selectList(entity);
    }

    @Test
    public void testBatchExec() {
        UserEntity entity = new UserEntity();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        IUserDao dao = context.getBean("userDaoImpl", IUserDao.class);
        dao.batchExec(entity);
    }


}
