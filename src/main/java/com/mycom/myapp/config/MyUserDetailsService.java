package com.mycom.myapp.config;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mycom.myapp.user.entity.User;
import com.mycom.myapp.user.entity.UserRole;
import com.mycom.myapp.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService{

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Optional<User> optionalUser = userRepository.findByUserEmail(email);			
		
		if(optionalUser.isPresent()) {
			
			User user = optionalUser.get();
			List<UserRole> listUserRole = user.getUserRoles();
			
//			//SimpleGrantedAuthority가 role 하나하나라고 이해하면됨.
//			//roles()를 쓰면 ROLE_를 붙여주는데 이거는 안붙여줘서 우리가 따로 넣어줘야한다.
//			List<SimpleGrantedAuthority> authorities =
//					listUserRole.stream().map(UserRole::getName).map(name->"ROLE_"+name).map(SimpleGrantedAuthority::new).toList();

			//MyUserDetails 사용			
			UserDetails userDetails = MyUserDetails.builder()
					.username(user.getUserName())
					.password(user.getUserPassword())
					.email(user.getUserEmail())					
					.build();	
			return userDetails;
		}
		
		throw new UsernameNotFoundException(email);
		
	}
}
