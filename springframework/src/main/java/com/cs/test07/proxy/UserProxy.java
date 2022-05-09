package com.cs.test07.proxy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class UserProxy {
    // 前置通知
    @Before("execution(* com.cs.test07.dao.*.*(..))")
    public void before() {
        System.out.println("before execute .............");
    }

    // 后置通知
    @AfterReturning("execution(* com.cs.test07.dao.*.*(..))")
    public void afterReturning() {
        System.out.println("afterReturning executor ................");
    }

    // 最终通知
    @After("execution(* com.cs.test07.dao.*.*(..))")
    public void after() {
        System.out.println("after executor ................");
    }

    // 异常通知
    @AfterThrowing("execution(* com.cs.test07.dao.*.*(..))")
    public void afterThrowing() {
        System.out.println("afterThrowing  afterThrowing ................");
    }

    // 环绕通知
    @Around("execution(* com.cs.test07.dao.*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) {
        System.out.println("before around ......");
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("after around ......");
        return proceed;
    }


}
