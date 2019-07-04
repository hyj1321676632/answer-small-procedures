package com.study.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        /**
         * 添加拦截路径
         */
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/admin/**");
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/exercise/**");
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/index/**");
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/choose/**");
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/delete/**");
        super.addInterceptors(registry);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * 解决静态资源被拦截问题
         */
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }

    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }
}
