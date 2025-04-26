package com.mycom.myapp.auth.service;

import com.mycom.myapp.user.dto.UserResultDto;

public interface AuthService {
	public UserResultDto login(String email,String password);
}
