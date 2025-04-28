package com.mycom.myapp.auth.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mycom.myapp.user.dto.UserDto;
import com.mycom.myapp.user.dto.UserResultDto;
import com.mycom.myapp.user.entity.User;
import com.mycom.myapp.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
	
	private final UserRepository userRepository;
	
	
	public UserResultDto login(String email,String password) {
		UserResultDto userResultDto = new UserResultDto();
		Optional<User> optionalUser = userRepository.findByUserEmail(email);
		
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();
			if(user.getUserPassword().equals(password)) {
				//User -> UserDto (user 정보로 userDto를 만들기)
				UserDto userDto = UserDto.builder()
						.userId(user.getUserId())
						.userEmail(user.getUserEmail())
						.userName(user.getUserName())																	
						.build();				
				userResultDto.setUserDto(userDto);
				
				userResultDto.setResult("success");
			}else {
				userResultDto.setResult("fail");
			}
		}
		
		return userResultDto;
	}
}
