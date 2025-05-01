package com.mycom.myapp.auth.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.mycom.myapp.user.dto.UserResultDto;
import com.mycom.myapp.user.entity.User;

//오류 나는 코드
//✅ 원인 분석
//이 메시지에서 핵심은 testClass = com.mycom.myapp.auth.service.AuthServiceTest2인데, 이 테스트 클래스가 @WebMvcTest를 사용하는데도 Service 계층을 테스트하려 했기 때문일 가능성이 큽니다.
//@WebMvcTest는 컨트롤러 테스트 전용이라 @Service, @Repository 같은 빈들을 로딩하지 않습니다.


@WebMvcTest(AuthServiceImpl.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthServiceTest2 {
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private AuthServiceImpl authServiceImpl;
	
	
	
	@Test
	@Order(1)
	void login_success() {
	    // given
	    String email = "test@example.com";
	    String password = "1234";
//	    User mockUser = User.builder()
//	            .userId(1)
//	            .userEmail(email)
//	            .userPassword(password)
//	            .userName("홍길동")
//	            .build();
//
//	    when(userRepository.findByUserEmail(email)).thenReturn(Optional.of(mockUser));

	    UserResultDto result = authServiceImpl.login(email, password);

	    assertEquals("success", result.getResult(), "로그인이 성공했어야 합니다.");
	    assertEquals(email, result.getUserDto().getUserEmail(), "User 이메일이 일치해야 합니다.");
	    assertEquals("홍길동", result.getUserDto().getUserName(), "User 이름이 일치해야 합니다.");
	}

	@Test
	void login_passwordIncorrect() {
	    // given
	    String email = "test@example.com";
	    String password = "wrongpassword";
//	    User mockUser = User.builder()
//	            .userId(1)
//	            .userEmail(email)
//	            .userPassword("correctpassword")
//	            .userName("홍길동")
//	            .build();

//	    when(userRepository.findByUserEmail(email)).thenReturn(Optional.of(mockUser));

	    // when
	    UserResultDto result = authServiceImpl.login(email, password);

	    // then
	    assertEquals("passWordIncorrect", result.getResult(), "비밀번호가 틀렸을 때 'passWordIncorrect'가 반환되어야 합니다.");
	}

	@Test
	void login_idNotFound() {
	    // given
	    String email = "notfound@example.com";
	    String password = "anyPassword";

//	    when(userRepository.findByUserEmail(email)).thenReturn(Optional.empty());

	    // when
	    UserResultDto result = authServiceImpl.login(email, password);

	    // then
	    assertEquals("idNotFound", result.getResult(), "존재하지 않는 이메일일 경우 'idNotFound'가 반환되어야 합니다.");
	}

}


