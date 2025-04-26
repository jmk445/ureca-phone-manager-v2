package com.mycom.myapp.user.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
public class UserResultDto {	
	private String result;
	private UserDto userDto;
	private List<UserDto> userList;
	
}
