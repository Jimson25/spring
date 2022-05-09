### AOP的一些术语
- 连接点
  > 一个类中可以被增强的方法被称为连接点
- 切入点
  > 全部连接点中实际被真正增强的方法叫切入点
- 通知
  > 实际被增强的部分叫通知
  - 前置通知 > 切入点执行之前执行
  - 后置通知 > 切入点执行之后执行
  - 环绕通知 > 切入点执行前后都会执行
  - 异常通知 > 出现异常时执行
  - 最终通知 > 执行完切入点代码之后执行，类似finally  
- 切面
  > 把通知应用到切入点的过程

### 切入点表达式
语法结构( 其中'\'用于转义 )
> execution(\[权限修饰符]\[返回类型]\[类全路径]\[方法名称]([参数列表]))

### 使用举例
对com.cs.test07.dao.UserDaoImpl的add()方法增强  
> execution(* com.cs.test07.dao.UserDaoImpl.add(..))  
> 其中'*'为权限修饰符，表示所有权限  
> 返回类型可以省略，这里没有添加
> com.cs.test07.dao.UserDaoImpl 为类全路径名
> add为方法名称，add(..)表示方法名为add参数任意的方法，即不对参数列表做限制

对com.cs.test07.dao.UserDaoImpl的所有方法增强
> execution(* com.cs.test07.dao.UserDaoImpl.*(..))

对com.cs.test07.dao包的所有类里面的所有方法增强
> execution(* com.cs.test07.dao.\*.*(..))  


### 配置方式
1. 在spring配置类上添加@EnableAspectJAutoProxy注解开启aspect生成代理对象
```java
@Configuration
// 使用注解配置开启aspect生成代理对象
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages = "com.cs.test07.*")
public class SpringConfig {

}
```

2. 新建AOP配置类配置通知操作，这里可以通过 @Pointcut注解配置一个通用的切入点，
   后面可以在通知上使用被该注解修饰的方法名来使用注解中的切入点表达式,如pointCut()方法
   
3. 当存在多个切面配置类时，可以在类上添加@order注解来配置多个配置类的执行顺序
```java
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
```
