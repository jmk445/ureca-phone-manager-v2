package com.mycom.myapp.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myapp.auth.service.AuthService;
import com.mycom.myapp.user.dto.UserResultDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class AuthController {
	private final AuthService authService;
	@PostMapping("/login")
	public UserResultDto login(@RequestParam("email") String email,
							   @RequestParam("password") String password) {
		UserResultDto userResultDto = authService.login(email, password);
		return userResultDto;
	}
	
	
}
