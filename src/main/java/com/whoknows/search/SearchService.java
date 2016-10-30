package com.whoknows.search;

import com.whoknows.reply.RelpyService;
import com.whoknows.user.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.whoknows.wkMessage.search.SearchTopicResponse;
import com.whoknows.wkMessage.search.SearchUserResponse;
import com.whoknows.wkMessage.search.TopicResult;
import com.whoknows.wkMessage.user.UserSummaryInfo;
import java.util.ArrayList;

@Service
public class SearchService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final int pageSize = 20;

	@Autowired
	private SearchDAO searchDAO;
	@Autowired
	private UserService userService;
	@Autowired
	private RelpyService relpyService;

	public SearchUserResponse searchUserByKeyWord(String key, Integer page, SearchType type, boolean vip) {
		log.info("search user:{}", key);
		if (StringUtils.isEmpty(key)
				|| page == null) {
			return null;
		}

		SearchUserResponse searchUserResponse = new SearchUserResponse();

		try {
			searchUserResponse.setKeyWord(key);
			searchUserResponse.setPageSize(pageSize);
			searchUserResponse.setTotalPage(100);
			searchUserResponse.setCurrentPage(page);
			searchUserResponse.setUsers(new ArrayList<>());

			searchDAO.searchUserByKeyWord(key, page, pageSize, type, vip).parallelStream().forEach(user -> {
				UserSummaryInfo userSummaryInfo = new UserSummaryInfo();
				userSummaryInfo = userService.getUserSummaryInfo(user.getId());
				searchUserResponse.getUsers().add(userSummaryInfo);
			});
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}

		return searchUserResponse;
	}

	public SearchTopicResponse searchTopicByKeyWord(String key, Integer page, SearchType type) {
		log.info("search topic:{}", key);
		if (StringUtils.isEmpty(key)
				|| page == null) {
			return null;
		}

		SearchTopicResponse searchResponse = new SearchTopicResponse();
		try {
			searchResponse.setKeyWord(key);
			searchResponse.setPageSize(pageSize);
			searchResponse.setTotalPage(100);
			searchResponse.setCurrentPage(page);
			searchResponse.setTopicResults(new ArrayList<>());

			searchDAO.searchTopicByKeyWord(key, page, pageSize, type).stream().forEach(topic -> {
				TopicResult topicResult = new TopicResult();
				topicResult.setTopic(topic);
				searchResponse.getTopicResults().add(topicResult);
			});

			searchDAO.searchTagByKeyWord(key, page, pageSize, type).stream().forEach(topic -> {
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
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}

		return searchResponse;
	}
}
