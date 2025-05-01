package com.mycom.myapp.auth.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.mycom.myapp.user.dto.UserResultDto;
import com.mycom.myapp.user.entity.User;
import com.mycom.myapp.user.repository.UserRepository;

// //-> by GPT
@SpringBootTest
@AutoConfigureMockMvc
public class AuthServiceTest {
	
	@Mock
    private UserRepository userRepository;

	@InjectMocks
    private AuthServiceImpl authService;

	@Test
	void login_success() {
	    // given
	    String email = "alice@example.com";
	    String password = "pass123";
	    User mockUser = User.builder()
	            .userId(1)
	            .userEmail(email)
	            .userPassword(password)
	            .userName("홍길동")
	            .build();
//
	    when(userRepository.findByUserEmail(email)).thenReturn(Optional.of(mockUser));

	    UserResultDto result = authService.login(email, password);

	    assertEquals("success", result.getResult(), "로그인이 성공했어야 합니다.");
	    assertEquals(email, result.getUserDto().getUserEmail(), "User 이메일이 일치해야 합니다.");
	    assertEquals("Alice", result.getUserDto().getUserName(), "User 이름이 일치해야 합니다.");
	}

	@Test
	void login_passwordIncorrect() {
	    // given
	    String email = "alice@example.com";
	    String password = "wrongpassword";
//	    User mockUser = User.builder()
//	            .userId(1)
//	            .userEmail(email)
//	            .userPassword("correctpassword")
//	            .userName("홍길동")
//	            .build();

//	    when(userRepository.findByUserEmail(email)).thenReturn(Optional.of(mockUser));

	    // when
	    UserResultDto result = authService.login(email, password);

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
	    UserResultDto result = authService.login(email, password);

	    // then
	    assertEquals("idNotFound", result.getResult(), "존재하지 않는 이메일일 경우 'idNotFound'가 반환되어야 합니다.");
	}

}


