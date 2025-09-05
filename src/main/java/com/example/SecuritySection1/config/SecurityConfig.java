package com.example.SecuritySection1.config;

import static org.springframework.security.config.Customizer.withDefaults;

import java.io.IOException;
import java.net.URI;
import java.util.Collections;

import javax.sql.DataSource;

import org.apache.tomcat.util.file.ConfigurationSource;
import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import exceptionHandling.CustomBasicAuthenticationEntryPoint;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
@Profile("!prod")
public class SecurityConfig {

    @Bean
    public
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(corsConfig-> corsConfig.configurationSource(new CorsConfigurationSource() {
			
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration cors = new CorsConfiguration();
				cors.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
				cors.setAllowedMethods(Collections.singletonList("*"));
				cors.setAllowCredentials(true);
				cors.setAllowedHeaders(Collections.singletonList("*"));
				cors.setMaxAge(3600l);
				return cors;
			}
		}))       
        
        .requiresChannel(rec-> rec.anyRequest().requiresInsecure())
            .csrf(csrf->csrf.disable())
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/notice","/contact","/register").permitAll()
                .requestMatchers("/myAccount","/myBalance").authenticated()
                .anyRequest().authenticated()
            );
        http.formLogin();
        http.httpBasic(withDefaults()); 
        http.httpBasic(hce-> hce.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint())); 
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
    	return new BCryptPasswordEncoder();
    			
    }
    
}

