package com.club.club_manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.club.club_manager.aop.PointC;
import com.club.club_manager.commom.CommonResult;
import com.club.club_manager.entity.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: lhz
 * @date: 2020/10/26
 **/
public interface UserService extends IService<User> {
    CommonResult<String> register(String username,String password);

    CommonResult<String> login(String username,String password);

     void addRequire(User user) throws Exception;

     void addRequire2(User user) ;



     void addRequiteException(User user) throws Exception;
     void addRequireNew(User user) throws Exception;

     void addRequiteExceptionNew(User user) throws Exception ;

     void addRequireNESTED(User user) ;

     void addRequiteExceptionNESTED(User user) throws Exception ;
}
