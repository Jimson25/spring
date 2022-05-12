package com.cs.test10.dao;

import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements IUserDao{
    @Override
    public int insert() {
        System.out.println("IUserDaoImpl.insert");
        return 0;
    }
}
