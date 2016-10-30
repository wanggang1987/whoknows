package com.whoknows.search;

import com.whoknows.reply.RelpyService;
import com.whoknows.user.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.whoknows.wkMessage.search.SearchResponse;
import com.whoknows.wkMessage.search.TopicResult;
import java.util.ArrayList;

@Service
public class SearchService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SearchDAO searchDAO;
	@Autowired
	private UserService userService;
	@Autowired
	private RelpyService relpyService;

	public SearchResponse searchByKeyWord(String key, Integer page) {
		log.info("search:{}", key);
		if (StringUtils.isEmpty(key)) {
			return null;
		}

		SearchResponse searchResponse = new SearchResponse();
		searchResponse.setKeyWord(key);
		searchResponse.setPageSize(20);
		searchResponse.setTotalPage(100);
		searchResponse.setCurrentPage(page);
		searchResponse.setTopicResults(new ArrayList<>());

		searchDAO.searchTopicByKeyWord(key).stream().forEach(topic -> {
			TopicResult topicResult = new TopicResult();
			topicResult.setTopic(topic);
			searchResponse.getTopicResults().add(topicResult);
		});

		searchDAO.searchTagByKeyWord(key).parallelStream().forEach(topic -> {
			TopicResult topicResult = new TopicResult();
			topicResult.setTopic(topic);
			searchResponse.getTopicResults().add(topicResult);
		});

		searchResponse.getTopicResults().parallelStream().forEach(topicResult -> {
			topicResult.setTopicUser(userService.getUserSummaryInfo(topicResult.getTopic().getUser_id()));
			topicResult.setReply(relpyService.getHotReplyForRopic(topicResult.getTopic().getId()));
			if (topicResult.getReply() != null) {
				topicResult.setReplyUser(userService.getUserSummaryInfo(topicResult.getReply().getUser_id()));
			}
		});

		return searchResponse;
	}
}
