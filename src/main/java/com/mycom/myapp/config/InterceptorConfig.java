package com.mycom.myapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mycom.myapp.interceptor.LoginInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
		
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//	    registry.addInterceptor(new LoginInterceptor())
//	            .addPathPatterns("/**")
//	            .excludePathPatterns(
//	                "/login/**", 
//	                "/index.html", 
//	                "/users/**", 
//	                "/login.html", 
//	                "/register.html",
//	                "/css/**", 
//	                "/js/**", 
//	                "/images/**", 
//	                "/favicon.ico",
//	                "/static/**", 
//	                "/webjars/**"
//	            );
	}

}
