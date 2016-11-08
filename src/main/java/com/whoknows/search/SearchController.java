package com.whoknows.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/search")
public class SearchController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SearchService searchService;

	@RequestMapping(path = "/{page}", method = RequestMethod.GET)
	public ResponseEntity searchByKeyWord(String keyWord, @PathVariable("page") Integer page) {
		log.info("search :{}", keyWord);
		SearchTopicResponse searchResponse = searchService.searchTopicByKeyWord(keyWord, page, SearchType.time);
		if (searchResponse != null) {
			return ResponseEntity.ok(searchResponse);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
}
