package com.example.SecuritySection1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SecuritySection1.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	public Customer findByEmail(String email);
}
