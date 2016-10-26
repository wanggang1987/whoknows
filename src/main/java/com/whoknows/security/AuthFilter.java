package com.whoknows.security;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;

@Component
public class AuthFilter  extends AbstractAuthenticationProcessingFilter {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private static final String DEFAULT_PROCESSING_URL = "/login";
			
	public AuthFilter(AuthenticationProvider authProvider) {
		super(DEFAULT_PROCESSING_URL);
		setAuthenticationManager(new ProviderManager(Arrays.asList(authProvider)));
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		if (((HttpServletRequest) req).getMethod().equals("POST")) {
			super.doFilter(req, res, chain);
		} else {
			HttpServletResponse resp = (HttpServletResponse) res;
			resp.setHeader("Access-Control-Allow-Origin", "*");
			chain.doFilter(req, res);
		}
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException,IOException, ServletException {
		try {
			JsonObject obj = retrieveJsonBody(req);
			String user = obj.getString("username");
			String password = obj.getString("password");
			
			if (StringUtils.isEmpty(user) || StringUtils.isEmpty(password)) {
				throw new BadCredentialsException("Username and/or password is empty.");
			}
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, password);
			return getAuthenticationManager().authenticate(token);
		} catch (BadCredentialsException e) {
			log.error("Read user/passwd from http request error,{}." , e);
			throw e;
		}
		
	}
	
	private JsonObject retrieveJsonBody(HttpServletRequest request) throws IOException {
		String body = IOUtils.readLines(request.getInputStream()).stream().collect(Collectors.joining());
		log.info("http body of: {}", body);
		return Json.createReader(new StringReader(body)).readObject();

	}

}
