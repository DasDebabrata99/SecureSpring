package com.example.SecuritySection1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Profile("!prod")
public class CustomerAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	PasswordEncoder pe;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String pwd = authentication.getCredentials().toString();
		UserDetails user = userDetailsService.loadUserByUsername(username);
		return new UsernamePasswordAuthenticationToken(username, pwd, user.getAuthorities());
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
