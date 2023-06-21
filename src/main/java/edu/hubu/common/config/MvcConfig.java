package edu.hubu.common.config;

import edu.hubu.common.interceptor.ExamInterceptor;
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
                        "/member/**/login",
                        "/member/**/register"
                ); // 放行登陆和注册页面

        // 统一拦截测验模块的题目crud功能
        registry.addInterceptor(new ExamInterceptor())
                .addPathPatterns("/exam/question/**")
                .addPathPatterns("/exam/paper/**")
                .excludePathPatterns("/exam/comment/**");   // 用户可以访问

    }
}