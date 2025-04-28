package com.mycom.myapp.user.entity;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name="users")
public class User {	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private int userId;
	private String userName;
	private String userPassword;
	private String userEmail;
	private String userProfileImage;
	private Date userRegisterDate;
	
	@OneToMany(fetch=FetchType.EAGER)
	@ToString.Exclude
	private List<UserRole> userRoles = new ArrayList<>();
}
