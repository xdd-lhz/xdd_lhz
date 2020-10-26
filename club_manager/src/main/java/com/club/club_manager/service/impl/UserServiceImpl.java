package com.club.club_manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.club.club_manager.commom.CommonResult;
import com.club.club_manager.dao.UserMapper;
import com.club.club_manager.entity.User;
import com.club.club_manager.jwt.JwtUtil;
import com.club.club_manager.service.UserService;
import org.springframework.stereotype.Service;

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
}
