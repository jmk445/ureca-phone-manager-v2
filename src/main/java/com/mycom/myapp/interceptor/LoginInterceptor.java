package com.mycom.myapp.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mycom.myapp.user.dto.UserResultDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@Component
public class LoginInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("LoginInterceptor >>>>>" + request.getRequestURI());
		HttpSession session = request.getSession();
		UserResultDto userResultDto = (UserResultDto)session.getAttribute("userResultDto");		
		if (userResultDto == null) {
		    response.sendRedirect("/login.html"); // 로그인 페이지 URL로 리다이렉트
		    return false;
		}

		return true;		
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
	}
}
