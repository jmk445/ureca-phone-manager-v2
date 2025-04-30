package com.mycom.myapp.user.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mycom.myapp.user.entity.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	//crud 생성 완료

	Optional<User> findByUserEmail(String email);
	
	@Query("SELECT u FROM User u JOIN FETCH u.userRoles WHERE u.userRegisterDate < :cutoffDate AND u.modified = false")
	List<User> findAllRegisteredBefore(@Param("cutoffDate") Date cutoffDate);


}
