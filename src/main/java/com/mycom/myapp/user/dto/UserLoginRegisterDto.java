package com.mycom.myapp.user.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginRegisterDto {
	private int userId;
	private String userName;	
	private String userEmail;
	private String userProfileImage;
	private Date registerDate;
}
