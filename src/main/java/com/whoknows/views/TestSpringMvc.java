package com.whoknows.views;

import javax.websocket.server.PathParam;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping("/")
public class TestSpringMvc {

	@RequestMapping("print/{Str}")
	public String printStr(@PathParam("Str") String str){
		return "Hello " + str;
	}
}
