package com.mycom.myapp.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mycom.myapp.customer.entity.Customer;
import com.mycom.myapp.customer.repository.CustomerRepository;

@Service
public class CustomerService {
	
	private final CustomerRepository customerRepository;
	
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	public List<Customer> listCustomer(){
		return customerRepository.findAll();
	}
	public Optional<Customer> detailCustomer(int customerId) {
		return customerRepository.findById(customerId);
	}
	public Customer insertCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	public Optional<Customer> updateCustomer(Customer customer) {
		Optional<Customer> existingCustomer = customerRepository.findById(customer.getId());		
		if(existingCustomer.isPresent()) {
			return Optional.of(customerRepository.save(customer));
		}
		return Optional.empty();		
	}
	
	public void deleteCustomer(int customerId) {
		customerRepository.deleteById(customerId);
	}
	
}

