package com.itfactory.solution.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvmConfig implements WebMvcConfigurer {
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new Interceptor())
                .excludePathPatterns("/css/**", "/images/**", "/js/**");
    }
	
	//외부에서 백단 돌리기
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/back/**")
        		.allowedOrigins("*")
                .allowedMethods("GET", "POST");
    }
}
