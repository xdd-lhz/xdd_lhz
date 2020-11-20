package com.club.club_manager.aop;

import com.club.club_manager.entity.User;
import com.club.club_manager.service.impl.UserServiceImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: lhz
 * @date: 2020/10/29
 **/
@Aspect
@Component
public class MyAspect {
    @Autowired
    private UserServiceImpl userService;

    @Pointcut(value = "@annotation(com.club.club_manager.aop.PointC)")
    public void pointCut() {

    }

    @AfterReturning(value = "pointCut()")
    public void afterTest(JoinPoint joinpoint){
        try {
            userService.addRequiteException(new User("456", "789"));
            System.out.println("----------after--------------");
        } catch (Exception e) {
        }


    }

//    @Before(value = "pointCut()")
//    public void beforeTest() throws Exception {
//        userService.addRequiteExceptionNew(new User("456", "789"));
//    }
}
