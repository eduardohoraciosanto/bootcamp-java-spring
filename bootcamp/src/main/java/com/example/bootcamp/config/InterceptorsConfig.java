package com.example.bootcamp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry; 
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.bootcamp.interceptors.LoggingInterceptor;
import com.example.bootcamp.interceptors.XRequestIDInterceptor; 

@Configuration
public class InterceptorsConfig implements WebMvcConfigurer{
    // Register an interceptor with the registry, Interceptor name : RequestInterceptor 
	@SuppressWarnings("null")
    @Override
	public void addInterceptors(InterceptorRegistry registry) { 
        //Injects X-Request-ID to requests
        registry.addInterceptor(new XRequestIDInterceptor());

        //Logging Interceptor with some request parameters and X-Request-ID
		registry.addInterceptor(new LoggingInterceptor());
	}
}

