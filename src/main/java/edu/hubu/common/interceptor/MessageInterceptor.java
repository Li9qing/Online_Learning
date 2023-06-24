package edu.hubu.common.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hubu.common.utils.UserHolder;
import edu.hubu.member.dto.UserDto;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * MessageInterceptor for
 *
 * @Author yruns
 * @Version 2023/6/24
 */
public class MessageInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HashMap<String, Object> map = new HashMap<>();
        UserDto user = UserHolder.getUser();

        if (user.getAuthority() == 2) {
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
