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
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.example.SecuritySection1.filter.CsrfCookieFilter;

import exceptionHandling.CustomBasicAuthenticationEntryPoint;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
@Profile("!prod")
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		CsrfTokenRequestAttributeHandler csrfRequestHandler = new CsrfTokenRequestAttributeHandler();

		http.securityContext(context -> context.requireExplicitSave(false))
				.sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
				.cors(corsConfig -> corsConfig.configurationSource(new CorsConfigurationSource() {

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

				.requiresChannel(rec -> rec.anyRequest().requiresInsecure())
				.csrf(csrf -> csrf.csrfTokenRequestHandler(csrfRequestHandler)
						.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
				.addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
				.authorizeHttpRequests(request -> request
						.requestMatchers("/myAccount").hasAuthority("VIEWACCOUNT")
						.requestMatchers("/myCard").hasAuthority("VIEWCARD")
						.requestMatchers("/myBalance").hasAuthority("VIEWBALANCE")
						.requestMatchers("/myLoan").hasAuthority("VIEWLOAN")
						.requestMatchers("/user").authenticated()
						.requestMatchers("/notice", "/contact", "/register").permitAll());
						
		http.formLogin();
		http.httpBasic(withDefaults());
		http.httpBasic(hce -> hce.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
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
