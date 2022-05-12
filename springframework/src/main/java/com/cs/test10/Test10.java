package com.cs.test10;

import com.cs.test10.entity.UserEntity;
import org.junit.Test;
import org.springframework.context.support.GenericApplicationContext;

public class Test10 {
    @Test
    public void test01() {
        // public class AnnotationConfigApplicationContext extends GenericApplicationContext
        GenericApplicationContext context = new GenericApplicationContext();
        context.refresh();
        context.registerBean("user", UserEntity.class, UserEntity::new);

        UserEntity user = context.getBean("user", UserEntity.class);
        System.out.println(user);

    }


}
