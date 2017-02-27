package com.whoknows.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/properties")
public class PageContentController {

	@Autowired
	private ConfigProperties configProperties;

	@RequestMapping(path = "/{key}", method = RequestMethod.GET)
	public ResponseEntity getProperteis(@PathVariable("key") String key) {
		if (configProperties != null && configProperties.getProperties() != null
				&& configProperties.getProperties().containsKey(key)) {

			return ResponseEntity.ok(new ContentResponse(configProperties.getProperties().get(key)));
		}
		return ResponseEntity.notFound().build();
	}
}
