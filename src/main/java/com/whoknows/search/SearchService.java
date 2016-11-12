package com.whoknows.search;

import com.whoknows.reply.ReplyDetail;
import com.whoknows.topic.TopicDetail;
import com.whoknows.comment.CommentService;
import com.whoknows.domain.Reply;
import com.whoknows.domain.TargetType;
import com.whoknows.follow.FollowService;
import com.whoknows.like.LikeService;
import com.whoknows.reply.RelpyService;
import com.whoknows.user.UserDetail;
import com.whoknows.user.UserService;
import com.whoknows.utils.CommonFunction;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

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
			UserDetail user = userService.currentUser();

			SearchTopicResponse searchResponse = new SearchTopicResponse();
			searchResponse.setKeyWord(key);
			searchResponse.setPaging(new Paging());
			searchResponse.getPaging().setCurrentPage(page);
			searchResponse.getPaging().setPerPage(pageSize);
			searchResponse.setTopicResults(searchDAO.searchTopicByKeyWord(key, page, pageSize, type).parallelStream().map(topic -> {
				TopicResult topicResult = new TopicResult();
				TopicDetail topicDetail = new TopicDetail();
				topic.setTitle(CommonFunction.highLight(key, topic.getTitle()));
				topic.setContent(CommonFunction.highLight(key, topic.getContent()));
				topicDetail.setTopic(topic);
				topicDetail.setAuthor(userService.getUser(topic.getId()));
				topicDetail.setFollowCount(followService.followCount(topic.getId(), TargetType.topic));
				if (user != null && user.getId() != null) {
					topicDetail.setCurrentFollowed(followService.isFollowed(user.getId(), topic.getId(), TargetType.topic));
				}
				topicResult.setTopicDetail(topicDetail);

				Reply reply = relpyService.getHotReplyForRopic(topic.getId());
				if (reply != null) {
					ReplyDetail replyDetail = new ReplyDetail();
					reply.setContent(CommonFunction.highLight(key, reply.getContent()));
					replyDetail.setReply(reply);
					replyDetail.setAuthor(userService.getUser(reply.getUser_id()));
					replyDetail.setLikeCount(likeService.likeCount(reply.getId(), TargetType.reply));
					replyDetail.setCommentCount(commentService.commentCount(reply.getId()));
					if (user != null && user.getId() != null) {
						replyDetail.setCurrentLiked(likeService.isLiked(user.getId(), reply.getId(), TargetType.reply));
					}
					topicResult.setReplyDetail(replyDetail);
				}
				return topicResult;
			}).collect(Collectors.toList()));
			return searchResponse;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
