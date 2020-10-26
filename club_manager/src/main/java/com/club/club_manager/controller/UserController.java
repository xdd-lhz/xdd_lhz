package com.club.club_manager.controller;

import com.club.club_manager.commom.CommonResult;
import com.club.club_manager.entity.User;
import com.club.club_manager.entity.param.UserParam;
import com.club.club_manager.jwt.JwtUtil;
import com.club.club_manager.service.UserService;
import com.club.club_manager.util.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lhz
 * @date: 2020/10/26
 **/
@RestController
@Api("用户接口")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 注册登录
     */
    @ApiOperation("注册")
    @PostMapping("/register")
    public CommonResult<String> register(@RequestBody UserParam user) {
        return userService.register(user.getUsername(),user.getPassword());
    }
    /**
     * 登录
     */
    @ApiOperation("登录")
    @PostMapping("/login")
    public CommonResult<String> login(@RequestBody UserParam user) {
        return userService.login(user.getUsername(),user.getPassword());
    }
    @ApiOperation("哈喽")
    @PostMapping("/hello")
    public CommonResult<String> hello(@LoginUser User user) {
        return CommonResult.succeed("hello"+user.getUsername());
    }


}
