package com.club.club_manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.club.club_manager.aop.PointC;
import com.club.club_manager.commom.CommonResult;
import com.club.club_manager.dao.UserMapper;
import com.club.club_manager.entity.User;
import com.club.club_manager.jwt.JwtUtil;
import com.club.club_manager.service.UserService;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: lhz
 * @date: 2020/10/26
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    @Override
    public CommonResult<String> register(String username,String password) {
        User userInDB = lambdaQuery()
                .eq(User::getUsername, username)
                .one();
        if (userInDB != null) {
            return CommonResult.failed("用户名存在");
        }
        User user = new User(username,password);
        save(user);
        return CommonResult.succeed("注册成功");
    }

    @Override
    public CommonResult<String> login(String username,String password) {
        User userInDB = lambdaQuery()
                .eq(User::getUsername,username)
                .one();
        if (userInDB == null) {
            return CommonResult.failed("用户名不存在");
        }
        if (!userInDB.getPassword()
                .equals(password)) {
            return CommonResult.failed("密码错误");
        }
        return CommonResult.succeed(JwtUtil.createToken(userInDB));

    }

    @PointC
    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void addRequire(User user) {
        save(user);
    }
    @Override
    public void addRequire2(User user) {
        save(user);
    }

    //@PointC
    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void addRequiteException(User user) throws Exception {
        save(user);
        throw new Exception("1111");
    }
    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
    public void addRequireNew(User user) {
        save(user);
    }
    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
    public void addRequiteExceptionNew(User user) throws Exception {
        save(user);
        throw new Exception("11111");
    }
    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.NESTED)
    public void addRequireNESTED(User user) {
        save(user);
    }
    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.NESTED)
    public void addRequiteExceptionNESTED(User user) throws Exception {
        save(user);
        throw new Exception("11111");
    }



}
