package com.fullstackmusic.server.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import javax.crypto.KeyGenerator;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class JwtUtil {
    /**
     * Token过期时间30分钟
     */
    public static final long EXPIRE_TIME = 30 * 60 * 1000;

    public static final String SECRET = "Hello";

    /**
     * 校验token是否正确
     */
    public static boolean verify(String token, String username) {
        try {
            // 设置加密算法
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                .withClaim("username", username)
                .build();
            // 效验TOKEN
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 生成签名,30min后过期
     */
    public static String sign(String username) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        // 附带username信息
        return JWT.create()
            .withClaim("username", username)
            .withExpiresAt(date)
            .sign(algorithm);

    }

    /**
     * 获得用户名
     */
    public static String getUserNameByToken(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("username").asString();
    }
}
