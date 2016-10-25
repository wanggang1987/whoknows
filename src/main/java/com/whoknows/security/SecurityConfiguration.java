package com.whoknows.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	private final Logger log = LoggerFactory.getLogger(SecurityConfiguration.class);
    
	@Autowired 
	private AuthProvider authProvider;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Bean
	public AuthFilter authFilter() {
		AuthFilter authFilter = new AuthFilter(authProvider);
		authFilter.setAuthenticationSuccessHandler(new AuthSuccessHandler());
		authFilter.setAuthenticationFailureHandler(new AuthFailedHandler());
		return authFilter;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/login*").permitAll()
				.antMatchers("/images/**").permitAll()
				.antMatchers("/styles/**").permitAll()
				.antMatchers("/fronts/**").permitAll()
				.antMatchers("/components/**").permitAll()
				.antMatchers("/bower_components/**").permitAll()
				.anyRequest().authenticated()
			.and()
				.formLogin()
					.loginPage("/login").permitAll()
					
			.and()
				.logout()
					.permitAll()
			.and()
				.addFilterBefore(authFilter(),UsernamePasswordAuthenticationFilter.class).csrf().disable();
			
	}
	
}