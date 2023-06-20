package edu.hubu.common.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hubu.common.utils.JwtUtils;
import edu.hubu.common.utils.UserHolder;
import edu.hubu.member.dto.UserDto;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * ExamInterceptor for Exam模块的统一鉴权
 *
 * @Author yruns
 * @Version 2023/6/20
 */
public class ExamInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HashMap<String, Object> map = new HashMap<>();
        UserDto user = UserHolder.getUser();

        if (user.getAuthority() > 0) {
            // 教师或管理员用户
            return true;
        }

        map.put("code", 401);
        map.put("msg", "权限不足，无法完成后续操作");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(map);
        response.setContentType("application/json;charset=utf8;");
        response.getWriter().write(json);

        return false;
    }
}
