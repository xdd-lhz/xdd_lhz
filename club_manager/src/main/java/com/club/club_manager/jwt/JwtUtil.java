package com.club.club_manager.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.club.club_manager.entity.User;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author: lhz
 * @date: 2020/10/26
 **/
public class JwtUtil {

    public static String createToken(User user) {
        String token = "";
        token = JWT.create().withExpiresAt(addDays(1)) //生存时间为1天
                .withAudience(user.getId()+"")
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }

    /**
     * 获取token里的用户id
     * @param token
     * @return
     */
    public static Integer parseUserId(String token) {
        String userId = JWT.decode(token).getAudience().get(0);
        return Integer.valueOf(userId);
    }

    /**
     * 获取过期时间
     * @param token
     * @return
     */
    public static Date etExpires(String token) {
        return JWT.decode(token)
                .getExpiresAt();
    }

    /**
     * 验证是否过期
     * @param token
     * @return
     */
    public static Boolean validToken(String token) {
        return new Date().before(etExpires(token));
    }


    private static Date localDateToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    private static Date addDays(int days) {
        LocalDateTime now = LocalDateTime.now();
        return localDateToDate(now.plusDays(1));
    }


}
