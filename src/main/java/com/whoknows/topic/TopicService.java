package com.whoknows.topic;

import com.whoknows.domain.ActionType;
import com.whoknows.domain.TargetType;
import com.whoknows.domain.Topic;
import com.whoknows.follow.FollowService;
import com.whoknows.reply.RelpyService;
import com.whoknows.tag.TagService;
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
	private TagService tagService;
	@Autowired
	private UserService userService;
	@Autowired
	private RelpyService relpyService;
	@Autowired
	private FollowService followService;

	public boolean createTopic(TopicCreate topicCreate) {
		if (topicCreate.getTopic() == null
				|| topicCreate.getTopic().getUser_id() == null
				|| StringUtils.isEmpty(topicCreate.getTopic().getTitle())
				|| StringUtils.isEmpty(topicCreate.getTopic().getContent())) {
			return false;
		}
		topicCreate.getTopic().setAction(ActionType.pending.toString());

		try {
			Long topicId = topicRepository.createTopic(topicCreate.getTopic());
			if(topicCreate.getTags() != null){
				topicCreate.getTags().parallelStream().forEach(tag -> {
					tagService.addTagRelation(topicId, tag.getValue());
				});
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
			return false;
		}
	}

	public Topic getTopic(Long id) {
		if (id == null) {
			return null;
		}

		try {
			return topicRepository.getTopic(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public TopicDetail getTopicDetail(Long id) {
		if (id == null) {
			return null;
		}

		try {
			TopicDetail topicDetail = new TopicDetail();
			topicDetail.setTopic(topicRepository.getTopic(id));
			if (topicDetail.getTopic() != null) {
				topicDetail.setAuthor(userService.getUser(topicDetail.getTopic().getUser_id()));
				topicDetail.setFollowCount(followService.followCount(topicDetail.getTopic().getId(), TargetType.topic));
				topicDetail.setReplys(relpyService.getReplyDetails(topicDetail.getTopic().getId(), 1));
			}
			return topicDetail;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
