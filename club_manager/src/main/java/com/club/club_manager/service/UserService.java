package com.club.club_manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.club.club_manager.commom.CommonResult;
import com.club.club_manager.entity.User;

/**
 * @author: lhz
 * @date: 2020/10/26
 **/
public interface UserService extends IService<User> {
    CommonResult<String> register(String username,String password);

    CommonResult<String> login(String username,String password);
}
