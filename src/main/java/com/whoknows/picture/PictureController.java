/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whoknows.picture;

import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/picture")
public class PictureController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PictureService pictureService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	InputStream getPicture(HttpServletRequest request) {
		//url id
		Long id = null;
		return pictureService.getPicture(id);
	}
}
