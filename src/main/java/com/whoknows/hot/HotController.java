package com.whoknows.hot;

import com.whoknows.search.SearchService;
import com.whoknows.search.SearchType;
import com.whoknows.wkMessage.search.SearchTopicResponse;
import com.whoknows.wkMessage.search.SearchUserResponse;
import com.whoknows.wkMessage.search.TopicResult;
import com.whoknows.wkMessage.user.UserSummaryInfo;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/hot")
public class HotController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HotService hotService;
	@Autowired
	private SearchService searchService;

	@RequestMapping(path = "/vip", method = RequestMethod.GET)
	public ResponseEntity listHotVip() {
		List<UserSummaryInfo> vips = hotService.listHotVip();
		if (vips != null) {
			return ResponseEntity.ok(vips);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@RequestMapping(path = "/topic", method = RequestMethod.GET)
	public ResponseEntity listHotTopic() {
		List<TopicResult> topics = hotService.listHotTopic();
		if (topics != null) {
			return ResponseEntity.ok(topics);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@RequestMapping(path = "/topic/{page}", method = RequestMethod.GET)
	public ResponseEntity searchTopicByKeyWord(String keyWord , @PathVariable("page") Integer page) {
		SearchTopicResponse searchResponse = searchService.searchTopicByKeyWord(keyWord, page, SearchType.rank);
		if (searchResponse != null) {
			return ResponseEntity.ok(searchResponse);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@RequestMapping(path = "/vip/{page}", method = RequestMethod.GET)
	public ResponseEntity searchVipyKeyWord(String keyWord , @PathVariable("page") Integer page) {
		SearchUserResponse searchResponse = searchService.searchUserByKeyWord(keyWord, page, SearchType.rank, true);
		if (searchResponse != null) {
			return ResponseEntity.ok(searchResponse);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
}
