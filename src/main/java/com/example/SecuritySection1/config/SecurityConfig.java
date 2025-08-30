package com.example.SecuritySection1.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/notice","/contact").permitAll()
                .requestMatchers("/myAccount","/myBalance").authenticated()
                .anyRequest().authenticated()
            );
        http.formLogin();
        http.httpBasic(withDefaults()); 
        return http.build();
    }
    
    @Bean
    public UserDetailsService createUserDetails() {
    	UserDetails user = User.withUsername("user").password("{noop}12345").build();
    	//bcrypt hash generated for password "54321" using th website "https://bcrypt-generator.com/"
    	UserDetails admin = User.withUsername("admin").password("{bcrypt}$2a$12$QdReAGY3RcaUCAXh3h7DceJ3YAJ/GfPrLmfaFWRa5LezgKRoaV3Ae").build();
    	return new InMemoryUserDetailsManager(user, admin);
    }
    
    @Bean
    public PasswordEncoder createPasswordEncoder() {
    	u
    	return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    
}

