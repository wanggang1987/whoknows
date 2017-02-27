package com.whoknows.app;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;
import javax.json.JsonValue.ValueType;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path="/properties")
public class PageContentController {

	@Autowired
	private ConfigProperties configProperties;
	
	@RequestMapping(path="/{key}", method= RequestMethod.GET)
	public ResponseEntity getProperteis(@PathVariable("key") String key){
		if(configProperties != null && configProperties.getProperties() != null 
				&& configProperties.getProperties().containsKey(key)) {
			
			return ResponseEntity.ok(new ContentResponse(configProperties.getProperties().get(key)));
		}
		return ResponseEntity.notFound().build();
	}
}
