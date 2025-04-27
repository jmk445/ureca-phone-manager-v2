package com.mycom.myapp.user.service;

import org.springframework.stereotype.Service;

import com.mycom.myapp.user.dto.UserDto;
import com.mycom.myapp.user.dto.UserResultDto;
import com.mycom.myapp.user.entity.User;
import com.mycom.myapp.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserResultDto register(User user) {
		UserResultDto userResultDto = new UserResultDto();		
		
		try {
			User newUser = this.userRepository.save(user);
			
			UserDto userDto = UserDto.builder()
					.userId(newUser.getUserId())
					.userEmail(newUser.getUserEmail())
					.userName(newUser.getUserName())
					.userProfileImage(newUser.getUserProfileImage())
					.build();
			userResultDto.setUserDto(userDto);
			userResultDto.setResult("success");
		}catch(Exception e) {
			userResultDto.setResult("fail");
		}
				
		
		return userResultDto;
	}

}
