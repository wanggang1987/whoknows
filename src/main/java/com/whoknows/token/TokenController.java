package com.whoknows.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/token")
public class TokenController {

	@Autowired
	private TokenService tokenService;

	@RequestMapping(path = "/{userId}/{token}", method = RequestMethod.GET)
	public ResponseEntity continueRegister(@PathVariable("userId") Long userId, @PathVariable("token") String token) {
		if (tokenService.continueRegister(userId, token)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}

	}
}
