package com.study.system.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        /**
         * 添加拦截路径
         */
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/admin/**");
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/exercise/**");
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/index/**");
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/choose/**");
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/delete/**");
        super.addInterceptors(registry);
    }
}
