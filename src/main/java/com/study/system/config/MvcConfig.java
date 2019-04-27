package com.study.system.config;

import com.study.system.component.WebLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer{

    /**
     * 将自定义的国际化语言组件注册到容器中
     * @return
     */
    @Bean(name="localeResolver")
    public LocaleResolver LocaleResolver(){
        return new WebLocaleResolver();
    }
}
