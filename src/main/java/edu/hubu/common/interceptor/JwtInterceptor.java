package edu.hubu.common.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hubu.common.utils.JwtUtils;
import edu.hubu.common.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * JwtInterceptor for
 *
 * @Author yruns
 * @Version 2023/6/19
 */
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 推荐前端发送请求将 token 放在 header
        String token = request.getHeader("Authorization");
        HashMap<String,Object> map = new HashMap<>();

        log.info("拦截到 " + request.getRequestURI());

        try {
            // 予以放行
            DecodedJWT verify  = JwtUtils.verify(token);// 验证令牌
            map.put("state",true);
            map.put("msg","验证token成功!");

            // 将用户id写入request
            UserHolder.saveUserId(verify.getClaim("userId").asLong());
            return true;

        } catch (SignatureVerificationException e) {// 签名匹配异常
            map.put("msg","无效签名!");
            e.printStackTrace();
        } catch (TokenExpiredException e){
            e.printStackTrace();
            map.put("msg","token已经过期!");
        } catch (AlgorithmMismatchException e){
            e.printStackTrace();
            map.put("msg","算法异常!");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","无效签名!");
        }

        map.put("state",false);// 设置状态

        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(map);
        response.setContentType("application/json;charset=utf8;");
        response.getWriter().write(json);

        return false;
    }

}