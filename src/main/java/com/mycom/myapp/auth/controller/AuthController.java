//package com.mycom.myapp.auth.controller;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.mycom.myapp.auth.service.AuthService;
//import com.mycom.myapp.user.dto.UserResultDto;
//
//import jakarta.servlet.http.HttpSession;
//import lombok.RequiredArgsConstructor;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/users")
//public class AuthController {
//	private final AuthService authService;
//	@PostMapping("/login")
//	public UserResultDto login(@RequestParam("email") String email,
//							   @RequestParam("password") String password, HttpSession session) {
//		
//		UserResultDto userResultDto = authService.login(email, password);
//			
//		session.setAttribute("userResultDto", userResultDto); // session에다 담아준다.	
//		return userResultDto;
//	}
//	
//	@GetMapping("/logout")
//	public UserResultDto logout(HttpSession session) {
//		session.invalidate();
//		UserResultDto userResultDto = new UserResultDto();
//		userResultDto.setResult("success");		
//		
//		return userResultDto;
//	}
//}
