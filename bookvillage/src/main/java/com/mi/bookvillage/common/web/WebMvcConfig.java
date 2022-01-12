package com.mi.bookvillage.common.web;

import com.mi.bookvillage.common.interceptor.JwtTokenAuthInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
@Slf4j
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${resources.location}")
    private String location;

    @Value("${resources.path_patterns}")
    String path_patterns;

    private JwtTokenAuthInterceptor jwtTokenAuthInterceptor;

    public WebMvcConfig(JwtTokenAuthInterceptor jwtTokenAuthInterceptor) {
        this.jwtTokenAuthInterceptor = jwtTokenAuthInterceptor;
    }



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("Loading Images");

        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:/Users/miyoung/ebrainSoft_study/resources/book_village/images/upload")
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }

    /**
     * Interceptor 등록 / 제외 URL 추가
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtTokenAuthInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/*/login", "/book/list", "/book/detail/*");

    }
}