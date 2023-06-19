package edu.hubu.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * JwtUtils for
 *
 * @Author yruns
 * @Version 2023/6/19
 */
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtUtils {
    /**
     * 定义 token 返回头部
     */
    public static String header;

    /**
     * token 前缀
     */
    public static String tokenPrefix;

    /**
     * 签名密钥
     */
    public static String secret;

    /**
     * 有效期
     */
    public static long expireTime;

    /**
     * 存进客户端的 token 的 key 名
     */
    public static final String USER_LOGIN_TOKEN = "token";


    /**
     * 设置 token 头部
     * @param header token 头部
     */
    public void setHeader(String header) {
        JwtUtils.header = header;
    }

    /**
     * 设置 token 前缀
     * @param tokenPrefix token 前缀
     */
    public void setTokenPrefix(String tokenPrefix) {
        JwtUtils.tokenPrefix = tokenPrefix;
    }

    /**
     * 设置 token 密钥
     * @param secret token 密钥
     */
    public void setSecret(String secret) {
        JwtUtils.secret = secret;
    }

    /**
     * 设置 token 有效时间
     * @param expireTimeInt token 有效时间
     */
    public void setExpireTime(int expireTimeInt) {
        JwtUtils.expireTime = expireTimeInt;
    }

    /**
     * 创建 TOKEN
     * JWT 构成: header, payload, signature
     * @param sub jwt 所面向的用户，即用户名
     * @return token 值
     */
    public static String createToken(String sub, Long userId) {
        return tokenPrefix + JWT.create()
                .withSubject(sub)
                .withClaim("userId", userId)
                .withExpiresAt(new Date(System.currentTimeMillis() + expireTime * 1000))
                .sign(Algorithm.HMAC256(secret));
    }

    /**
     * 验证 token
     * @param token 验证的 token 值
     * @return 用户名
     */
    /**
     * 验证token合法性
     */
    public static DecodedJWT verify(String token) {
        // 去除 token 前缀
        token = token.replace(tokenPrefix, "");
        // 如果有问题的话，那么抛出各种异常
        return JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
        // 使用JWT的require，生成一个验证对象
    }

    /**
     * 检查 token 是否需要更新
     * @param token token 值
     * @return 过期时间
     */
    public static boolean isNeedUpdate(String token) throws Exception {
        // 获取 token 过期时间
        Date expiresAt = null;
        try {
            expiresAt = JWT.require(Algorithm.HMAC512(secret))
                    .build()
                    .verify(token.replace(tokenPrefix, ""))
                    .getExpiresAt();
        } catch (TokenExpiredException e){
            return true;
        } catch (Exception e){
            throw new Exception(("token 验证失败"));
        }
        // 需要更新
        return (expiresAt.getTime() - System.currentTimeMillis()) < (expireTime >> 1);
    }
}

