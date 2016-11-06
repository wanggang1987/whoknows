package com.whoknows.topic;

import com.whoknows.domain.ActionType;
import com.whoknows.domain.TargetType;
import com.whoknows.domain.Topic;
import com.whoknows.follow.FollowService;
import com.whoknows.reply.RelpyService;
import com.whoknows.user.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TopicRepository topicRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private RelpyService relpyService;
	@Autowired
	private FollowService followService;

	public boolean createTopic(Topic topic) {
		topic.setAction(ActionType.pending.toString());
		if (topic.getUser_id() == null
				|| StringUtils.isEmpty(topic.getTitle())
				|| StringUtils.isEmpty(topic.getContent())) {
			return false;
		}

		try {
			topicRepository.createTopic(topic);
			return true;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return false;
		}
	}

	public boolean updateTopic(Topic topic) {
		if (topic.getId() == null
				|| StringUtils.isEmpty(topic.getTitle())
				|| StringUtils.isEmpty(topic.getContent())) {
			return false;
		}

		try {
			topicRepository.updateTopic(topic);
			return true;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return false;
		}
	}

	public boolean deleteTopic(Long id) {
		if (id == null) {
			return false;
		}

		try {
			topicRepository.deleteTopic(id);
			return true;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return false;
		}
	}

	public TopicDetail getTopic(Long id) {
		if (id == null) {
			return null;
		}

		TopicDetail topicDetail = new TopicDetail();
		try {
			topicDetail.setTopic(topicRepository.getTopic(id));
			if (topicDetail.getTopic() != null) {
				topicDetail.setAuthor(userService.getUser(topicDetail.getTopic().getUser_id()));
				topicDetail.setFollowCount(followService.followCount(topicDetail.getTopic().getId(), TargetType.topic));
				topicDetail.setReplys(relpyService.getReplyDetails(topicDetail.getTopic().getId(), 1));
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return null;
		}
		return topicDetail;
	}
}
