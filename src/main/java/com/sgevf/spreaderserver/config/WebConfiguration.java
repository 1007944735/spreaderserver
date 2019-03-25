package com.sgevf.spreaderserver.config;

import com.sgevf.spreaderserver.config.Interceptor.JWTInterceptor;
import com.sgevf.spreaderserver.config.Interceptor.LogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Autowired
    private LogInterceptor logInterceptor;
    @Autowired
    private JWTInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor).addPathPatterns("/**");
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/**").excludePathPatterns("/S0001").excludePathPatterns("/S0000");
    }
}
