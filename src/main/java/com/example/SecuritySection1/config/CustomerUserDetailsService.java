package com.example.SecuritySection1.config;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.SecuritySection1.model.Customer;
import com.example.SecuritySection1.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService{

	@Autowired
	private  CustomerRepository customerRepository;
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		Customer customer = customerRepository.findByEmail(username);
//		
//		List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(customer.getRole()));
//		return new User(username, customer.getPwd(), authorities);
//	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    Optional<Customer> customer = customerRepository.findByEmail(username);
	    if (customer == null) {
	        throw new UsernameNotFoundException("User not found with email: " + username);
	    }

	    List<GrantedAuthority> authorities = 
	            List.of(new SimpleGrantedAuthority("ROLE_" + customer.get().getRole()));

	    return new User(customer.get().getEmail(), customer.get().getPwd(), authorities);
	}

}
