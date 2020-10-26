package com.club.club_manager.config;

import com.club.club_manager.commom.CommonResult;
import com.club.club_manager.entity.User;
import com.club.club_manager.exception.MyException;
import com.club.club_manager.jwt.JwtUtil;
import com.club.club_manager.service.UserService;
import com.club.club_manager.util.SpringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: lhz
 * @date: 2020/10/26
 **/

public class AuthenticationInterceptor implements HandlerInterceptor {

    private UserService userService = SpringUtils.getBean(UserService.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token"); // 从 http 请求头中取出 token
        if (StringUtils.isEmpty(token)) {
            returnJson(response,"未登录");

        }
        User user=null;
        try {
            user = userService.getById(JwtUtil.parseUserId(token));
        } catch (Exception e) {
            returnJson(response,"token错误");
            return false;
        }
        if (user == null) {
            returnJson(response,"token错误");
            return false;

        }

        if (!JwtUtil.validToken(token)) {
            returnJson(response,"token过期");
            return false;
        }
        return true;
    }

    private void returnJson(HttpServletResponse response,String msg){
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(new ObjectMapper().writeValueAsString(CommonResult.failed(CommonResult.RetCode.UNAUTHORIZED,msg,null)));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(writer != null){
                writer.close();
            }
        }
    }
}
