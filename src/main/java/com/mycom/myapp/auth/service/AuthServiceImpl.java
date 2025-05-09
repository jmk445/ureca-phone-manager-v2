package com.mycom.myapp.auth.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mycom.myapp.user.dto.UserDto;
import com.mycom.myapp.user.dto.UserResultDto;
import com.mycom.myapp.user.entity.User;
import com.mycom.myapp.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService{
	
	private final UserRepository userRepository;
	
	
	public UserResultDto login(String email,String password) {
		UserResultDto userResultDto = new UserResultDto();
		Optional<User> optionalUser = userRepository.findByUserEmail(email);
		
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();
			if(user.getUserPassword().equals(password)) {
				//User -> UserDto (user 정보로 userDto를 만들기), password는 넣어주지 않는다.
				UserDto userDto = UserDto.builder()
						.userId(user.getUserId())
						.userEmail(user.getUserEmail())					
						.userName(user.getUserName())																	
						.build();				
				userResultDto.setUserDto(userDto);
				log.debug(user.getUserPassword());
				log.debug(password);
				System.out.println("in login : "+userResultDto);
				userResultDto.setResult("success");
			}else {
				userResultDto.setResult("passWordIncorrect");
			}
		}else {
			userResultDto.setResult("idNotFound");
		}
		
		return userResultDto;
	}
}
