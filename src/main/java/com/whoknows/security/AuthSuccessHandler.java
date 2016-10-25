package com.whoknows.security;

import java.io.IOException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.whoknows.domain.User;
import com.whoknows.security.tools.CookieTools;


@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler{
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest hsr, HttpServletResponse hsrp, Authentication auth) throws IOException, ServletException {
		
		User user = (User) auth.getPrincipal();
		
		log.info("Successful login ,user: {}.", user.getEmail());
		hsrp.setStatus(HttpServletResponse.SC_OK);
		hsrp.setContentType("application/json");

		CookieTools.addAuthCookie("userName", user.getEmail(), hsrp, hsr);
		JsonObject userObj = Json.createObjectBuilder()
				.add("username", user.getEmail())
				.add("authenticated", true)
				.build();
		Json.createWriter(hsrp.getWriter()).writeObject(userObj);
		hsrp.getWriter().flush();
		
	}
	

}
