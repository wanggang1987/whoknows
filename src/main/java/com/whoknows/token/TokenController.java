package com.whoknows.token;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.whoknows.security.CookieTools;
import org.springframework.beans.factory.annotation.Value;

@Controller
@RequestMapping("/token")
public class TokenController {

	@Autowired
	private TokenService tokenService;

	@Value("${domain}")
	private String domain;

	@RequestMapping(path = "/{userId}/{token}", method = RequestMethod.GET)
	public ResponseEntity continueRegister(HttpServletResponse hsrp, HttpServletRequest hsr, @PathVariable("userId") Long userId, @PathVariable("token") String token) throws IOException {
		if (tokenService.continueRegister(userId, token)) {
			hsrp.sendRedirect("http://" + domain + "/p/#/login");
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}

	}
}
