package edu.hubu.common.config;

import edu.hubu.common.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * MvcConfig
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())
                .addPathPatterns("/**/*") // 禁止所有的非登陆页面
                .excludePathPatterns(
                        "/member/user/login",
                        "/member/user/register"); // 放行登陆和注册页面
    }
}