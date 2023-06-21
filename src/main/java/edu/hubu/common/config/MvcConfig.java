package edu.hubu.common.config;

import edu.hubu.common.interceptor.ExamInterceptor;
import edu.hubu.common.interceptor.GroupInterceptor;
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
                        "/member/**/register",
                        "/**/**/public/**"
                ).order(0); // 放行登陆和注册页面

        // 统一拦截测验模块请求
        registry.addInterceptor(new ExamInterceptor())
                .addPathPatterns("/exam/**/*")
                .excludePathPatterns("/exam/submit/user/**")
                .excludePathPatterns("/exam/comment/user/**")
                .excludePathPatterns(
                        "/**/**/public/**"
                ).order(1);

        // 统一拦截群组模块的题目crud功能
        registry.addInterceptor(new GroupInterceptor())
                .addPathPatterns("/group/**/*")
                .excludePathPatterns(
                        "/**/**/public/**"
                ).order(2);

    }
}