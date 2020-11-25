package com.club.club_manager.controller;

import com.club.club_manager.commom.CommonResult;
import com.club.club_manager.entity.User;
import com.club.club_manager.service.TestService;
import com.club.club_manager.service.UserService;
import com.club.club_manager.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.sound.midi.Soundbank;

/**
 * @author: lhz
 * @date: 2020/10/29
 **/
@RestController
public class PropagationController {
    @Autowired
    UserService userService;
    @Autowired
    TestService testService;



    @PostMapping("/test01")
    public CommonResult test() throws Exception {
        userService.addRequireNew(new User("123","456"));
        return CommonResult.succeed("666");
    }


    @PostMapping("/test22")
    public CommonResult test2() throws Exception {
        testService.test03(new User("123","456"));
        return CommonResult.succeed("666");
    }









    @ResponseBody
    @PostMapping("/test02")
    public CommonResult testb() throws Exception {
        userService.addRequire(new User("1233","4566"));
        return CommonResult.succeed();

    }
    @ResponseBody
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    @PostMapping("/test01c")
    public CommonResult testc() {
        try {
            userService.addRequireNESTED(new User("123","456"));

            userService.addRequiteExceptionNESTED(new User("789","963"));
        } catch (Exception e) {
            return CommonResult.succeed();

        }
        return CommonResult.succeed();

    }

}
