package com.mycom.myapp.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycom.myapp.customer.entity.Customer;


public interface CustomerRepository extends JpaRepository<Customer,Integer>{	
}
