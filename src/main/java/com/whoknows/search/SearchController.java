package com.whoknows.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.whoknows.wkMessage.search.SearchResponse;
@Controller
@RequestMapping("/search")
public class SearchController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SearchService searchService;

	@RequestMapping(path = "/{key}", method = RequestMethod.GET)
	public ResponseEntity searchByKeyWord(@PathVariable("key") String key) {
		SearchResponse searchResponse = searchService.searchByKeyWord(key);
		if (searchResponse != null) {
			return ResponseEntity.ok(searchResponse);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
}
