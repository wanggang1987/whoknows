package com.whoknows.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	private final Logger log = LoggerFactory.getLogger(SecurityConfiguration.class);
    
	@Autowired 
	private AuthProvider authProvider;
	
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
				// static resource permit all
				.antMatchers("index.html", "/app/**", "/p/**", "login*").permitAll()
				.antMatchers("/images/**", "/styles/**", "/fronts/**", "/components/**", "/bower_components/**").permitAll()
				// spring mvn controller permit all
				.antMatchers(HttpMethod.POST, "/login*").permitAll()
				.antMatchers("/user/current").permitAll()
				.antMatchers(HttpMethod.PUT, "/user").permitAll()
				.antMatchers("/token/**").permitAll()
				.antMatchers(HttpMethod.GET, "/img/**").permitAll()
				// other spring mvn controller need auth
				.antMatchers("/search/**").authenticated()
				.antMatchers("/tag/**").authenticated()
				.antMatchers("/topic/**").authenticated()
				.antMatchers(HttpMethod.POST, "/img/**").authenticated()
				.antMatchers("/hot/**").authenticated()
				.antMatchers("/reply/**").authenticated()
				.antMatchers("/follow/**").authenticated()
				.antMatchers("/like/**").authenticated()
				.antMatchers("/comment/**").authenticated()
				.antMatchers("/vip/**").authenticated()
				.antMatchers("/paper/**").authenticated()
				.anyRequest().authenticated()
			.and()
				.formLogin().loginPage("/p/#/login").permitAll().and().csrf().disable()
				.logout().permitAll().logoutUrl("/logout").logoutSuccessUrl("/")
			.and()
				.addFilterBefore(authFilter(),UsernamePasswordAuthenticationFilter.class).csrf().disable()
				.exceptionHandling().authenticationEntryPoint(authEntryPoint());
			
	}
	
	@Bean
	public AuthenticationEntryPoint authEntryPoint() {
		return new Http403ForbiddenEntryPoint();
	}

	
}
