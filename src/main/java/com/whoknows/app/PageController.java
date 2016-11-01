package com.whoknows.app;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	private final Logger log = LoggerFactory.getLogger(PageController.class);

	
	@RequestMapping("/p/")
	public String direct() {
		return "index";
	}

	@RequestMapping("/p/{page}")
	public String direct(@PathVariable("page") String page) {
		return page;
	}

	@RequestMapping("/p/{dir}/{page}")
	public String direct(@PathVariable("dir") String dir, @PathVariable("page") String page) {
		String finalPage = dir + "/" + page;
		return finalPage;
	}

	@RequestMapping("/p/{dir}/{dir2}/{page}")
	public String direct(@PathVariable("dir") String dir, @PathVariable("dir2") String dir2, @PathVariable("page") String page) {
		String finalPage = dir + "/" + dir2 + "/" + page;
		log.trace("Trying to render page: {}", finalPage);
		return finalPage;
	}
	
	@RequestMapping("/p/{dir}/{dir2}/{dir3}/{page}")
	public String direct(@PathVariable("dir") String dir, @PathVariable("dir2") String dir2, @PathVariable("dir3") String dir3, @PathVariable("page") String page) {
		String finalPage = dir + "/" + dir2 + "/" + dir3 + "/" + page;
		return finalPage;
	}
	
	@RequestMapping("/p/{dir}/{dir2}/{dir3}/{dir4}/{page}")
	public String direct(@PathVariable("dir") String dir, @PathVariable("dir2") String dir2, @PathVariable("dir3") String dir3, @PathVariable("dir4") String dir4, @PathVariable("page") String page) {
		String finalPage = dir + "/" + dir2 + "/" + dir3 + "/" + dir4 + "/" + page;
		return finalPage;
	}
}
