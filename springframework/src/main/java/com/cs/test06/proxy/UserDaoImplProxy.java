package com.cs.test06.proxy;

import com.cs.test06.dao.IUserDao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * jdk动态代理类
 */
public class UserDaoImplProxy implements InvocationHandler {
    // 被代理的对象
    private final IUserDao instance;

    public UserDaoImplProxy(IUserDao instance) {
        this.instance = instance;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 一些增强逻辑
        System.out.println("method: " + method.getName() + " \r\nparams: " + Arrays.toString(args));
        Object res = method.invoke(instance, args);
        System.out.println("res :" + res);
        return res;
    }
}
