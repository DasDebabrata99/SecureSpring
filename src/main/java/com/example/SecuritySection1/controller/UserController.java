package com.example.SecuritySection1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.SecuritySection1.model.Customer;
import com.example.SecuritySection1.repository.CustomerRepository;

@RestController
public class UserController {

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	PasswordEncoder pe;
	
	@PostMapping("/register")
	public void saveCustomer(@RequestBody Customer customer) {
		String hashpwd = pe.encode(customer.getPwd());
		customer.setPwd(hashpwd);
		customerRepository.save(customer);
	}
}
