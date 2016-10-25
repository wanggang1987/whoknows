package com.whoknows.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;

@Component
public class AuthFilter  extends AbstractAuthenticationProcessingFilter {

	private AuthProvider autoProvider;
	
	private static final String DEFAULT_PROCESSING_URL = "/login";
			
	public AuthFilter(AuthProvider autoProvider) {
		super(DEFAULT_PROCESSING_URL);
		this.autoProvider =autoProvider;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest arg0,
			HttpServletResponse arg1) throws AuthenticationException,
			IOException, ServletException {
		// TODO Auto-generated method stub
		return null;
	}

}
