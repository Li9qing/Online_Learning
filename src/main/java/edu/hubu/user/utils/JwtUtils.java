package edu.hubu.user.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * Jwt工具类
 */
public class JwtUtils {
    public static final long EXPIRE = 1000 * 60 * 60 * 24; // 过期时间为1天
    public static final String APP_SECRET = "rtdl2022group5";

    public static String getJwtToken(String id, String username,String role) {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")

                .setSubject("edu-user") // 分类
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))

                // token主体部分，存储用户信息
                .claim("id", id)
                .claim("username", username)
                .claim("role", role)
                // 签名
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();
    }
    public static String getJwtToken(String id, String username) {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")

                .setSubject("edu-user") // 分类
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))

                // token主体部分，存储用户信息
                .claim("id", id)
                .claim("username", username)
                // 签名
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();
    }
    public static boolean checkToken(String jwtToken) {
        if (StringUtils.isEmpty(jwtToken)) return false;
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static String getRole(String jwtToken) {
        if (StringUtils.isEmpty(jwtToken)) return "";
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (String) claims.get("teacherAccess");
    }

    public static String getId(String jwtToken) {
        if (StringUtils.isEmpty(jwtToken)) return "";
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (String) claims.get("id");
    }
}