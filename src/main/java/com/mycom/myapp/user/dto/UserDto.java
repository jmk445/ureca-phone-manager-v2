package com.mycom.myapp.user.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mycom.myapp.user.entity.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	private int userId;
	private String userName;
	private String userPassword;
	private String userEmail;
	private String userProfileImage;
	private Date registerDate;
	private List<UserRole> userRoles;
	private Boolean modified;
}
