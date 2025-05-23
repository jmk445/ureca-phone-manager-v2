package com.mycom.myapp.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class MyAuthenticationSuccessHandler  implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(
			HttpServletRequest request, 
			HttpServletResponse response,
			Authentication authentication
			
	) throws IOException, ServletException {
		// ajax로 로그인 성공의 결과를 return 
		response.setStatus(HttpServletResponse.SC_OK); //200
		response.setContentType("application/json");
		System.out.println("test");
		String jsonStr = """
				{"result" : "success"}
				""";
		response.getWriter().write(jsonStr);
		
	}
}
