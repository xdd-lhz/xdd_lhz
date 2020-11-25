package com.club.club_manager.service;

import com.club.club_manager.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: lhz
 * @date: 2020/11/25
 **/
@Service
public class TestService {
    @Autowired
    UserService userService;


    public void test01(User user) {
        try {
            userService.addRequire(user);
            userService.addRequiteException(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Transactional(rollbackFor = Exception.class)
    public void test02(User user) {
        try {
            userService.addRequire(user);

            userService.addRequiteException(user);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Transactional(rollbackFor = Exception.class)
    public void test03(User user) {
        try {
            userService.addRequire(user);

            userService.addRequiteException(user);

        } catch (Exception e) {
        }

    }

    @Transactional(rollbackFor = Exception.class)
    public void test04(User user) throws Exception {
            userService.addRequire(user);
            userService.addRequiteException(user);
    }

}
