package com.example.SecuritySection1.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.SecuritySection1.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>{

	public Optional<Customer> findByEmail(String email);
}
