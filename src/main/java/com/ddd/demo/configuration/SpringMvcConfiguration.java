package com.ddd.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * spring MVC通用配置类
 *
 * @author wl
 * @date 2021-2-4
 */
@Configuration
public class SpringMvcConfiguration implements WebMvcConfigurer {

    /**
     * 注入拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加网关消费者名称拦截器
//        UserContextInterceptor userContextInterceptor = new UserContextInterceptor();
//        registry.addInterceptor(userContextInterceptor).addPathPatterns("/**");
    }

}
