package com.mycom.myapp.user.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
			//todo : controller단에서 transactional로 인해서 생기는 exception handle을 못하는 문제를 해결하면 안된다. -> 4월 25일 수업 내용 참고
			//-> flush를 우리가 먼저 선제적으로 한다.
			UserRole userRole;
			if(user.getUserRoles() !=null) {
				userRole = userRoleRepository.findByName(user.getUserRoles().get(0).getName());
			}else {
				userRole = userRoleRepository.findByName("ROLE_BRONZE");
			}
			
			List<UserRole> userRoles = List.of(userRole);
			user.setUserRoles(userRoles);						
			User newUser = this.userRepository.save(user);
			userRepository.flush();
			System.out.println(newUser);
			UserDto userDto = UserDto.builder()
					.userId(newUser.getUserId())
					.userEmail(newUser.getUserEmail())
					.userName(newUser.getUserName())
					.userProfileImage(newUser.getUserProfileImage())
					.userRoles(newUser.getUserRoles())
					.registerDate(newUser.getUserRegisterDate())
					.build();
			userResultDto.setUserDto(userDto);
			userResultDto.setResult("success");
		}catch(Exception e) {			
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			userResultDto.setResult("sameEmail");
		}
				
		
		return userResultDto;
	}
	
	public UserResultDto listUsers() {
		//todo : pagination
    	UserResultDto userResultDto = new UserResultDto();
    	List<User> userFromRepo = userRepository.findAll();
    	List<UserDto> userDtoList = new ArrayList<>();
    	
    	for(int i = 0; i < userFromRepo.size(); i++) {
    		User user = userFromRepo.get(i);
    		UserDto userDto = UserDto.builder()
    				.userId(user.getUserId())
    				.userName(user.getUserName())    				
    				.userPassword(user.getUserPassword())
    				.userEmail(user.getUserEmail())    	
    				.registerDate(user.getUserRegisterDate())
    				.userRoles(user.getUserRoles())
    				.build();    		
    		userDtoList.add(userDto);
    	}
		
		
		userResultDto.setUserList(userDtoList);
		
		return userResultDto;		
	}
	
	public UserResultDto listUsersUpgrade() {
	    UserResultDto userResultDto = new UserResultDto();

	    Date cutoff = null;
	    try {
	        Calendar cal = Calendar.getInstance(); // 현재 시간
	        cal.add(Calendar.DAY_OF_MONTH, -14);   // 14일 전으로 이동
	        cutoff = cal.getTime();                // Date 객체로 변환

	        System.out.println("14일 전 cutoff 날짜: " + cutoff);
	    } catch (Exception e) {
	        userResultDto.setResult("fail");
	        e.printStackTrace();
	        return userResultDto;
	    }

	    List<User> userFromRepo = userRepository.findAllRegisteredBefore(cutoff);
	    List<UserDto> userDtoList = new ArrayList<>();

	    for (User user : userFromRepo) {	    	
	        UserDto userDto = UserDto.builder()
	                .userId(user.getUserId())
	                .userName(user.getUserName())
	                .userPassword(user.getUserPassword())
	                .userEmail(user.getUserEmail())
	                .registerDate(user.getUserRegisterDate())
	                .userRoles(user.getUserRoles())
	                .build();
	        userDtoList.add(userDto);
	    }

	    userResultDto.setUserList(userDtoList);
	    userResultDto.setResult("success");

	    return userResultDto;
	}

	public UserResultDto detailUser(int id) {
		UserResultDto userResultDto = new UserResultDto();
    	Optional<User> userFromRepo = userRepository.findById(id);
    	
    	try {
    		if(userFromRepo.isPresent()) {
        		User user = userFromRepo.get();
        		UserDto userDto = UserDto.builder()
        				.userId(user.getUserId())
        				.userName(user.getUserName())    				
        				.userPassword(user.getUserPassword())
        				.userEmail(user.getUserEmail())
        				.userRoles(user.getUserRoles())
        				.registerDate(user.getUserRegisterDate())
        				.build(); 
        		    		
        		userResultDto.setUserDto(userDto);    
        		userResultDto.setResult("success");
        	}else {
        		userResultDto.setResult("fail");
        	}
    		
    	}catch(Exception e) {
    		e.printStackTrace();
			userResultDto.setResult("fail");
    	}
    	
		return userResultDto;		
	}; // 사용자 상세 조회
	
	@Transactional
	public UserResultDto updateUser(UserDto userDto) {
		Optional<User> existingUser = userRepository.findById(userDto.getUserId());    	
    	UserResultDto userResultDto = new UserResultDto();
    	    	    	   	    	    
		if(existingUser.isPresent()) {
			try {
				System.out.println(userDto.getUserRoles());
				UserRole userRole = userRoleRepository.findByName(userDto.getUserRoles().get(0).getName());
				User user = User.builder()
						.userId(userDto.getUserId())
						.userName(userDto.getUserName())    				
						.userPassword(userDto.getUserPassword())
						.userEmail(userDto.getUserEmail())    	
						.userRoles(List.of(userRole))		
						.userRegisterDate(userDto.getRegisterDate())
						.modified(userDto.getModified())
						.build(); 
				System.out.println(user.getUserRoles());
								
				User userInserted= userRepository.save(user);
				userResultDto.setUserDto(userDto);
				userResultDto.setResult("success");
			}catch(Exception e) {
				e.printStackTrace();
				userResultDto.setResult("fail");
			}
				
		}
		
		
		return userResultDto;				
				
		
	}; // 사용자 수정
	
	public void deleteUser(int id) {
		userRepository.deleteById(id);
	}; // 사용자 삭제


}
