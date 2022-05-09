package com.cs.test07.proxy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class UserProxy2 {
    // 抽取相同的切入点，后面使用这个切入点配置
    @Pointcut("execution(* com.cs.test07.dao.*.*(..))")
    public void pointCut(){}

    // 前置通知
    @Before("pointCut()")
    public void before() {
        System.out.println("UserProxy2 before execute .............");
    }

    // 后置通知
    @AfterReturning("pointCut()")
    public void afterReturning() {
        System.out.println("UserProxy2 afterReturning executor ................");
    }

    // 最终通知
    @After("pointCut()")
    public void after() {
        System.out.println("UserProxy2 after executor ................");
    }

    // 异常通知
    @AfterThrowing("pointCut()")
    public void afterThrowing() {
        System.out.println("UserProxy2 afterThrowing  afterThrowing ................");
    }

    // 环绕通知
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        System.out.println("UserProxy2 before around ......");
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("UserProxy2 after around ......");
        return proceed;
    }


}
