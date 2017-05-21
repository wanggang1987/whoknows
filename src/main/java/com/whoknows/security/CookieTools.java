package com.whoknows.security;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class CookieTools {

	public static void addAuthCookie(String key, String value, HttpServletResponse resp, HttpServletRequest hsr) {
		final Cookie cookie = new Cookie(key, value);
		cookie.setPath("/");
		cookie.setMaxAge(600);
		if (!hsr.getRequestURL().toString().contains("localhost")) {
			cookie.setSecure(true);
		}
		resp.addCookie(cookie);
	}
}
