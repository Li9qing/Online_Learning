package edu.hubu.user.config;

import edu.hubu.user.interceptor.AuthenticationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @Author: yanjunfeng
 * @Description: TODO
 * @Date: Created in 下午3:47 2023/6/12
 * @Modified By:
 */
@Configuration
public class MyMVCConfig implements WebMvcConfigurer {
    @Resource
    AuthenticationInterceptor authenticationInterceptor;
    /**
     * 注册拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/**");
    }


    /**
     * 解决跨域请求
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //是否发送cookie
                .allowCredentials(true)
                //放行哪些原始域
                .allowedOrigins("*")
                //.allowedOriginPatterns("*")
                //放行哪些请求方法
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                //放行哪些请求头信息
                .allowedHeaders("*")
                //暴露哪些请求头信息
                .exposedHeaders("*");
    }
    @Configuration
    public class WebConfig implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            //项目中的所有接口都支持跨域
            registry.addMapping("/**")
                    //所有地址都可以访问，也可以配置具体地址
                    .allowedOrigins("*")
                    //允许的请求方式
                    .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                    //是否支持跨域Cookie
                    .allowCredentials(true)
                    // 跨域允许时间
                    .maxAge(3600);
        }
    }

}