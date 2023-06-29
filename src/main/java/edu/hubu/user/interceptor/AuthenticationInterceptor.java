package edu.hubu.user.interceptor;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hubu.common.utils.R;
import edu.hubu.user.entity.AdminDTO;
import edu.hubu.user.entity.UserDTO;
import edu.hubu.user.entity.UserEntity;
import edu.hubu.user.service.impl.UserServiceImpl;
import edu.hubu.user.utils.JwtUtils;
import edu.hubu.user.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Author: yanjunfeng
 * @Description: TODO
 * @Date: Created in 上午11:06 2023/6/19
 * @Modified By:
 */
@CrossOrigin
@Component
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    UserServiceImpl chk;
    @Override
    public boolean preHandle( HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws JsonProcessingException {
        //String token = request.getHeader("token");
        String headers = request.getHeader("AxiosHeaders");
//        log.info(headers,request.getHeader("idd"));
        //   log.info(request.getHeaders("token").nextElement());
        // 使用 JSON 解析器解析请求头部信息为 JSON 对象
//        ObjectMapper objectMapper = new ObjectMapper();
//        Map<String, Object> headersMap;
//        if(objectMapper.readValue(headers, Map.class)!=null){
//
//            String token = (String) objectMapper.readValue(headers, Map.class).get("token");
//        }


        String token = request.getHeader("token");


        // 从 JSON 对象中获取 token 字段的值

        log.info(request.toString());
        //检查token：在前端的cookie中，如果登陆，前端会主动插入token到cookie，并且每个请求经过前端拦截器包装，自带token
        log.info("getTOKEN" + token);
        String requestURI = request.getRequestURI();
        log.info(requestURI);
        //只对注册和登陆两个板块无条件放行（暂时：2023/6/21）
        if (requestURI.equals("/user/user/register") || requestURI.equals("/user/user/login")) {
            return true; // 放行接口
        }
        if (requestURI.equals("/user/admin/register") || requestURI.equals("/user/admin/login")) {
            return true; // 放行接口
        }
//        String userJson = stringRedisTemplate.opsForValue().get(token);
//        if(JSON.parseObject(userJson, UserDTO.class)==null||JSON.parseObject(userJson, AdminDTO.class)==null){//查无此人！
//            return false;
//        }
//        if(JSON.parseObject(userJson, UserDTO.class)!=null){
//            if(requestURI.contains("admin")){
//                return false;
//            }
//        }
        if (token == null) {
            log.info("got a null token lol");
//           ResponseUtil.out(response, R.error(50008,"未登录"));
//            for(int i =0;i<1000;i++){}
            return true;//没有token视为登出（登出同样会删除token）
        }
        UserEntity e1 = chk.getUserDetail(token);
        if(e1!=null){

            if(e1.getStatus()==1){
                log.info("got a BANNED lol");
                ResponseUtil.out(response, R.error(50008, "你以被举办！"));
                return false;
            }

        }
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            log.info("Not a Method");
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Class<?> declaringClass = method.getDeclaringClass();
        // 需要登录权限
        if (declaringClass.isAnnotationPresent(Login.class) ||
                method.isAnnotationPresent(Login.class) ||
                declaringClass.isAnnotationPresent(Teacher.class) ||
                method.isAnnotationPresent(Teacher.class)) {

            if (token == null) {
                log.info("got a null token lol");
                ResponseUtil.out(response, R.error(50008, "未登录"));
                return true;
            }
            if (!JwtUtils.checkToken(token)) {
                ResponseUtil.out(response, R.error(50014, "登录失效"));
                return false;
            }

        }
//        // 进一步判断是否需要教师权限
//        if (declaringClass.isAnnotationPresent(Teacher.class) || method.isAnnotationPresent(Teacher.class)) {
//            String role = JwtUtils.getRole(token);
//            if (!role.equals("teacher")) {
//                ResponseUtil.out(response, R.error(50015, "权限不足"));
//                return false;
//            }
//        }
        log.info("pass!!!!!!!!!!!!!");
        return true;
    }

}