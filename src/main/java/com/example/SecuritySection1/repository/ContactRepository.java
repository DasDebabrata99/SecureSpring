package com.example.SecuritySection1.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.SecuritySection1.model.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, String> {
	
	
}