package com.whoknows.vip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/vip")
public class VipController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(path = "/set/{userEmail}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity setVip(@PathVariable("userEmail") String userEmail) {
		return ResponseEntity.ok().build();
	}
}
