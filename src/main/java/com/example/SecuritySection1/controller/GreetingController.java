package com.example.SecuritySection1.controller;

import org.springframework.security.authentication.AnonymousAuthenticationProvider;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingController {

	@GetMapping("/hello")
	@ResponseBody
	public String greet() {
		
		
		return "Hey There! Welcome to secure network";
		
	}
}
