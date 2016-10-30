package com.whoknows.hot;

import com.whoknows.reply.RelpyService;
import com.whoknows.user.UserService;
import com.whoknows.wkMessage.search.TopicResult;
import com.whoknows.wkMessage.user.UserSummaryInfo;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HotDAO hotDAO;
	@Autowired
	private UserService userService;
	@Autowired
	private RelpyService relpyService;

	public List<UserSummaryInfo> listHotVip() {
		List<UserSummaryInfo> vips = new ArrayList<>();
		
		try {
			 hotDAO.listHotVip().parallelStream().forEach(user -> {
				 UserSummaryInfo userSummaryInfo = new UserSummaryInfo();
				 userSummaryInfo = userService.getUserSummaryInfo(user.getId());
				 vips.add(userSummaryInfo);
			 });
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
		return vips;
	}

	public List<TopicResult> listHotTopic() {
		List<TopicResult> topicResults = new ArrayList<>();

		try {
			hotDAO.listHotTopic().parallelStream().forEach(topic -> {
				TopicResult topicResult = new TopicResult();
				topicResult.setTopic(topic);
				topicResult.setTopicUser(userService.getUserSummaryInfo(topicResult.getTopic().getUser_id()));
				topicResult.setReply(relpyService.getHotReplyForRopic(topicResult.getTopic().getId()));
				if (topicResult.getReply() != null) {
					topicResult.setReplyUser(userService.getUserSummaryInfo(topicResult.getReply().getUser_id()));
				}
				topicResults.add(topicResult);
			});
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
		return topicResults;
	}
}
