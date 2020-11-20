package com.club.club_manager;

import com.club.club_manager.commom.CommonResult;
import com.club.club_manager.entity.User;
import com.club.club_manager.jwt.JwtUtil;
import com.club.club_manager.service.impl.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class ClubManagerApplicationTests {
    @Autowired
    private UserServiceImpl userService;


	@Test
	void contextLoads() throws JsonProcessingException {
        User user = new User();
        user.setId(1);
        user.setUsername("hahah");
        user.setPassword("123");
        String token = JwtUtil.createToken(user);
        System.out.println(token);
        Integer integer = JwtUtil.parseUserId("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxIiwiZXhwIjoxNjAzNzgwMjIwfQ.B-KZTetLa9g8riK5eEUqkti7O_I21x4PGlwJoSb2w50");
        System.out.println(integer);
        System.out.println(JwtUtil.validToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxIiwiZXhwIjoxNjAzNzgwMjIwfQ.B-KZTetLa9g8riK5eEUqkti7O_I21x4PGlwJoSb2w50"));
        System.out.println(JwtUtil.etExpires(token));

        System.out.println(new ObjectMapper().writeValueAsString(CommonResult.failed("1")));
    }
    @Transactional
    @Test
    @Rollback(value = false)
    void testPropagation() throws Exception {
       userService.addRequire(new User("::","12"));
    }


}
