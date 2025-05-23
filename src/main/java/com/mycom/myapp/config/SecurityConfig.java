package com.mycom.myapp.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecurityConfig {
	
	//security와 대화 수단
	@Bean
	SecurityFilterChain filterChain(
			HttpSecurity http,
			MyAuthenticationSuccessHandler successHandler,
			MyAuthenticationFailureHandler failureHandler			
			) throws Exception {
			
		return http
				.authorizeHttpRequests(
					request->{
						request.requestMatchers(								
				                "/login/**", 				                
				                "/api/users/**",
				                "/users/login",
				                "/login.html", 
				                "/register.html",
				                "/favicon.ico",				                                	              
				                "/assets/**",
				                "/swagger-ui/index.html",
				                "/swagger-ui/**",
				                "/swagger-ui.html"
								).permitAll()										
						.anyRequest().authenticated();
					}
				)
				//csrf 설정
				.csrf(csrf->csrf.disable()) //csrf를 아예 안쓰겠다.
//				.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())) //cookie로 csrf token를 넣어준다.(front,back 둘다에게?) 그 다음 부터는 이제 프론트에서 백엔드쪽으로 계쏙 token을 내려줘야 로그인 가능하다., cookie 기반으로 만들라고 요청을 하고, 
				.formLogin(form -> 
					form						
						.loginPage("/login.html")
						.loginProcessingUrl("/login") //이제 post로 로그인을 보내는데, 실제로 로그인이 processiong되는 곳이 어디냐를 알려주는 것입닏.
						//ajax요청 처리를 할시에 별도의 Handler, MyAuthenticationSuccessHandler, 즉 페이지 요청이 아닌 데이터 요청(ajax)이기 때문에 우리는 새로운 handler를 추가해줘야함(==result:succss이거만 내려주는 것}. 지금까지는defaultSuccessUrl("/",true)이걸로 처리했었음.  
						.successHandler(successHandler)
						.failureHandler(failureHandler)
						//.defaultSuccessUrl("/",true) ajax 요청할때 우리가 redirect하고 싶었는데 이게 실행돼서 에러 났던 거임. <doctype html뭐시기
						.permitAll()
						)
				.logout(logout->logout.permitAll()) // /logout이라는 url로 요청하면 자동으로 spring security가 session을 invalidate, 그럴 때 뭔가 수행하지 말라는 뜻								
				.build(); 
	}
	
	//spring security는 userdetalks라는 인증된 사용자 정보 객체를 가지고 있다. 우리는 아무것도 제공을 않나느데, 그러면 userdetails는 user라는 이르믕로 , password는 그 랜덤하게 만들어진 그것을 사용하게 된다. 그런데 이제 사용자마다 다르게 관리해야하기 때문에, 우리는 userDetailsService라는 것을 만들어서 이걸 customizing하기 때문에 myUserDetailService라는 것을 만들 거거든요., 우리 작성하지 않을 것이기 때문에, 자기만의 USerDetails가 아니라 , 우리가 작성한 myuserDetailsSErvice를 가지고 작동하도록 만들것이다. 그렇게 하면, 새로운 USerDetails를 만들어서 제공을 해야ㅣ 
	//그러니까, UserDetailsService, UserDetails 에서 MyUserDetails와 MyUserDetail을 사용하도록 변경해야함

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
