package com.mycom.myapp.phone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycom.myapp.phone.entity.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Integer> {
	Optional<Phone> findByPhoneName(String name);
}
