//package com.mycom.myapp.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import com.mycom.myapp.interceptor.LoginInterceptor;
//
//@Configuration
//public class InterceptorConfig implements WebMvcConfigurer {
//	@Autowired
//	private LoginInterceptor loginInterceptor;
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//	    registry.addInterceptor(loginInterceptor)
//	            .addPathPatterns("/**")
//	            .excludePathPatterns(
//	            	"/",
//	                "/login/**", 
//	                "/index.html", 
//	                "/api/users/**",
//	                "/users/login",
//	                "/login.html", 
//	                "/register.html",	               	               
//	                "/css/**", 
//	                "/js/**", 
//	                "/images/**", 
//	                "/favicon.ico",
//	                "/static/**",	                	               
//	                "/assets/**",
//	                "/swagger-ui/index.html",
//	                "/swagger-ui/**",
//	                "/swagger-ui.html"
//	            );
//	}
//
//}
