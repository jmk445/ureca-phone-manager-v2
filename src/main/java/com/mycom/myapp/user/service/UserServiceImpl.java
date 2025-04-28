package com.mycom.myapp.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mycom.myapp.user.dto.UserDto;
import com.mycom.myapp.user.dto.UserResultDto;
import com.mycom.myapp.user.entity.User;
import com.mycom.myapp.user.entity.UserRole;
import com.mycom.myapp.user.repository.UserRepository;
import com.mycom.myapp.user.repository.UserRoleRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;
	private final UserRoleRepository userRoleRepository;
	
	@Override
	@Transactional
	public UserResultDto register(User user) {
		UserResultDto userResultDto = new UserResultDto();		
		
		try {
			UserRole userRole = userRoleRepository.findByName("ROLE_BRONZE");
			List<UserRole> userRoles = List.of(userRole);
			user.setUserRoles(userRoles);						
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
