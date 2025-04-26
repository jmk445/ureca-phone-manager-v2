package com.mycom.myapp.customer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myapp.customer.entity.Customer;
import com.mycom.myapp.customer.service.CustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor //생성자 생성
@RequestMapping("/customers")
public class CustomerController {
	private final CustomerService customerService;	
	
	@GetMapping(value="/")
	public String customerMain() {
		return "customers";
	}
	
	@GetMapping("/list")
	@ResponseBody
	public List<Customer> listCustomer(Model model) {
		List<Customer> customerList = customerService.listCustomer();	
		return customerList;		
	}
	
	@GetMapping(value="/detail/{customerId}")
	public Optional<Customer> detailCustomer(@PathVariable int customerId) {
		System.out.println(customerId);
		return customerService.detailCustomer(customerId);
	}
	
	@PostMapping(value="/insert")
	public Customer insertCustomer(Customer customer) {
		System.out.println(customer);
		return customerService.insertCustomer(customer);		
	}
	

	@PostMapping(value="/update")
	public Optional<Customer> updateCustomer(Customer customer) {
		System.out.println(customer);
		return customerService.updateCustomer(customer);				
	}

	@GetMapping(value="/delete/{customerId}")
	public void deleteCustomer(@PathVariable int customerId){
		System.out.println(customerId);
		customerService.deleteCustomer(customerId);			
	}
}
