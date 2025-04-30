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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private int userId;
	private String userName;
	private String userPassword;
	private String userEmail;
	private String userProfileImage;
	private Date userRegisterDate;
	private Boolean modified;
	@OneToMany(fetch=FetchType.EAGER)
	@ToString.Exclude
	private List<UserRole> userRoles;
}
