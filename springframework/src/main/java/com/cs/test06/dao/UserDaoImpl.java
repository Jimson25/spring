package com.cs.test06.dao;

public class UserDaoImpl implements IUserDao {
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public void update(String id) {
        System.out.println(id);
    }
}
