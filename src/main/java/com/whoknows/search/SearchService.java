package com.whoknows.search;

import com.whoknows.reply.ReplyDetail;
import com.whoknows.topic.TopicDetail;
import com.whoknows.comment.CommentService;
import com.whoknows.domain.Reply;
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

		try {
			SearchTopicResponse searchResponse = new SearchTopicResponse();
			searchResponse.setKeyWord(key);
			searchResponse.setPaging(new Paging());
			searchResponse.getPaging().setCurrentPage(page);
			searchResponse.getPaging().setPerPage(pageSize);
			searchResponse.setTopicResults(new ArrayList<>());

			searchDAO.searchTopicByKeyWord(key, page, pageSize, type).parallelStream().forEach(topic -> {
				TopicResult topicResult = new TopicResult();
				TopicDetail topicDetail = new TopicDetail();
				topicDetail.setTopic(topic);
				topicDetail.setAuthor(userService.getUser(topic.getId()));
				topicDetail.setFollowCount(followService.followCount(topic.getId(), TargetType.topic));
				topicResult.setTopicDetail(topicDetail);

				Reply reply = relpyService.getHotReplyForRopic(topic.getId());
				if (reply != null) {
					ReplyDetail replyDetail = new ReplyDetail();
					replyDetail.setReply(reply);
					replyDetail.setAuthor(userService.getUser(reply.getUser_id()));
					replyDetail.setLikeCount(likeService.likeCount(reply.getId(), TargetType.reply));
					replyDetail.setCommentCount(commentService.commentCount(reply.getId()));
					topicResult.setReplyDetail(replyDetail);
				}
				searchResponse.getTopicResults().add(topicResult);
			});
			return searchResponse;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return null;
		}

	}
}
