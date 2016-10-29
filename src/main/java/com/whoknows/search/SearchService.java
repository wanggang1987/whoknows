package com.whoknows.search;


import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whoknows.wkMessage.search.SearchResponse;
@Service
public class SearchService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SearchDAO searchDAO;

	public SearchResponse searchByKeyWord(String key) {
		log.info("search:{}", key);
		if (StringUtils.isEmpty(key)) {
			return null;
		}

		SearchResponse searchResponse = new SearchResponse();

//		searchResponse.setTopicViews(searchDAO.searchTopicByKeyWord(key).stream().map(topic -> {
//			TopicView topicView = new TopicView();
//			topicView.setTopic(topic);
//			return topicView;
//		}).collect(Collectors.toList()));
//
//		searchResponse.getTopicViews().addAll(searchDAO.searchTagByKeyWord(key).stream().map(topic -> {
//			TopicView topicView = new TopicView();
//			topicView.setTopic(topic);
//			return topicView;
//		}).collect(Collectors.toList()));
//
//		searchResponse.setUserViews(searchDAO.searchUserByKeyWord(key).stream().map(user -> {
//			UserView userView = new UserView();
//			userView.setUser(user);
//			return userView;
//		}).collect(Collectors.toList()));

		return searchResponse;
	}
}
