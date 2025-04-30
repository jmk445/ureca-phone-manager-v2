package com.mycom.myapp.user.service;

import com.mycom.myapp.user.dto.UserDto;
import com.mycom.myapp.user.dto.UserResultDto;
import com.mycom.myapp.user.entity.User;


public interface UserService {
	
	public UserResultDto register(User user);
	UserResultDto listUsers(); // 전체 사용자 목록
	UserResultDto detailUser(int id); // 사용자 상세 조회
	UserResultDto updateUser(UserDto user); // 사용자 수정
	void deleteUser(int id); // 사용자 삭제
	public UserResultDto listUsersUpgrade();

}
