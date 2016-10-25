package com.whoknows.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthFailedHandler extends SimpleUrlAuthenticationFailureHandler{
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());


	@Override
	public void onAuthenticationFailure(HttpServletRequest hsr, HttpServletResponse response, AuthenticationException a) throws IOException, ServletException {
		log.debug("Authentication failed.");
		
		if(a instanceof BadCredentialsException){
			log.debug("invalid credentials");
		}
		super.onAuthenticationFailure(hsr, response, a);
	}
}
