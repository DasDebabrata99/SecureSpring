package com.example.SecuritySection1.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
    	UserDetails admin = User.withUsername("admin").password("{noop}54321").build();
    	return new InMemoryUserDetailsManager(user, admin);
    }
    
//    @Bean
//    public PasswordEncoder createPasswordEncoder()
    
    
}

