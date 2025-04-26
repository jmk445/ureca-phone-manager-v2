package com.mycom.myapp.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycom.myapp.user.entity.User;
import com.mycom.myapp.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/register")
	@ResponseBody
	public User register(User user){
		return this.userService.insertUser(user);		
	}
}
