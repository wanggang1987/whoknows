package com.whoknows.search;

import com.whoknows.comment.CommentService;
import com.whoknows.domain.TargetType;
import com.whoknows.follow.FollowService;
import com.whoknows.like.LikeService;
import com.whoknows.reply.RelpyService;
import com.whoknows.user.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.whoknows.topic.TopicResult;
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
	@Autowired
	private LikeService likeService;
	@Autowired
	private FollowService followService;
	@Autowired
	private CommentService commentService;

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
				topicResult.setTopicDetail(new TopicRusult());
				topicResult.getTopicDetail().setTopic(topic);
				searchResponse.getTopicResults().add(topicResult);
			});

			searchDAO.searchTagByKeyWord(key, page, pageSize, type).stream().forEach(topic -> {
				TopicResult topicResult = new TopicResult();
				topicResult.setTopicDetail(new TopicRusult());
				topicResult.getTopicDetail().setTopic(topic);
				searchResponse.getTopicResults().add(topicResult);
			});

			searchResponse.getTopicResults().parallelStream().forEach(topicResult -> {
				topicResult.getTopicDetail().setAuthor(userService.getUser(topicResult.getTopicDetail().getTopic().getId()));
				topicResult.getTopicDetail().setFollowCount(followService.followCount(topicResult.getTopicDetail().getTopic().getId(), TargetType.topic));
				topicResult.setReplyDetail(new ReplyResult());
				topicResult.getReplyDetail().setReply(relpyService.getHotReplyForRopic(topicResult.getTopicDetail().getTopic().getId()));
				if (topicResult.getReplyDetail().getReply()!= null) {
					topicResult.getReplyDetail().setAuthor(userService.getUser(topicResult.getReplyDetail().getReply().getUser_id()));
					topicResult.getReplyDetail().setLikeCount(likeService.likeCount(topicResult.getReplyDetail().getReply().getId(), TargetType.reply));
					topicResult.getReplyDetail().setCommentCount(commentService.commentCount(topicResult.getReplyDetail().getReply().getId()));
				}
			});
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return null;
		}

		return searchResponse;
	}
}
