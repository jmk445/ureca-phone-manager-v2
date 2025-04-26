package com.mycom.myapp.user.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mycom.myapp.user.entity.User;
import com.mycom.myapp.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public User insertUser(User user) {
		Map<String,String> map = new HashMap<>();
		
		User userResult = this.userRepository.save(user);
		
		
		return userResult;
	}

}
