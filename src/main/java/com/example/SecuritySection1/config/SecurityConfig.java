package com.example.SecuritySection1.config;

import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

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
        http.csrf(csrf->csrf.disable())
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/notice","/contact","/register").permitAll()
                .requestMatchers("/myAccount","/myBalance").authenticated()
                .anyRequest().authenticated()
            );
        http.formLogin();
        http.httpBasic(withDefaults()); 
        return http.build();
    }
    
//    @Bean
//    public UserDetailsService createUserDetails(DataSource dataSource) {
//    	
//    	return new JdbcUserDetailsManager(dataSource);
//    }
//    
    @Bean
    public PasswordEncoder createPasswordEncoder() {
    	return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    
}

