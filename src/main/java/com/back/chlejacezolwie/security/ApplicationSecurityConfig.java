package com.back.chlejacezolwie.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig{

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.sessionManagement(session ->
	    	session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
	    		);
	    http.sessionManagement(session ->
	    	session.maximumSessions(1));
	    http.csrf().disable();
	    http.authorizeRequests().anyRequest().permitAll();
	    return http.build();
	}

	
}
