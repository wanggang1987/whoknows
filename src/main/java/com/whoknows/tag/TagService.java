package com.whoknows.tag;

import com.whoknows.comment.CommentService;
import com.whoknows.domain.ActionType;
import com.whoknows.domain.Tag;
import com.whoknows.domain.TargetType;
import com.whoknows.follow.FollowService;
import com.whoknows.like.LikeService;
import com.whoknows.message.topic.TopicSelectResponse;
import com.whoknows.reply.RelpyService;
import com.whoknows.reply.ReplyDetail;
import com.whoknows.search.Paging;
import com.whoknows.search.TopicResult;
import com.whoknows.topic.TopicDetail;
import com.whoknows.user.UserService;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final int pageSize = 20;

	@Autowired
	private TagRepository tagRepository;
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

	public boolean addTag(Tag tag) {
		if (StringUtils.isEmpty(tag.getName())) {
			return false;
		}

		tag.setAction(ActionType.active.toString());
		try {
			tagRepository.addTag(tag);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return false;
		}
		return true;
	}

	public boolean deleteTag(Tag tag) {
		if (tag.getId() == null) {
			return false;
		}

		try {
			tagRepository.deleteTag(tag);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return false;
		}
		return true;
	}

	public List<TopicSelectResponse> getTagList() {
		try {
			return tagRepository.getTagList();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return null;
		}
	}

	public List<Tag> getTagList(String tagName) {
		try {
			return tagRepository.getTagList(tagName);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return null;
		}
	}

	public TagHomeRespone getTagHome(Long tagId, Integer page) {
		if (tagId == null || page == null) {
			return null;
		}

		try {
			TagHomeRespone tagHomeRespone = new TagHomeRespone();
			tagHomeRespone.setTag(tagRepository.getTag(tagId));
			tagHomeRespone.setTagFollowCount(followService.followCount(tagId, TargetType.tag));
			
			tagHomeRespone.setPaging(new Paging());
			tagHomeRespone.getPaging().setCurrentPage(page);
			tagHomeRespone.getPaging().setPerPage(pageSize);
			tagHomeRespone.setTopicResults(new ArrayList<>());

			tagRepository.getTopicByTag(tagId, page, pageSize).stream().forEach(topic -> {
				TopicResult topicResult = new TopicResult();
				topicResult.setTopicDetail(new TopicDetail());
				topicResult.getTopicDetail().setTopic(topic);
				tagHomeRespone.getTopicResults().add(topicResult);
			});

			tagHomeRespone.getTopicResults().parallelStream().forEach(topicResult -> {
				topicResult.getTopicDetail().setAuthor(userService.getUser(topicResult.getTopicDetail().getTopic().getId()));
				topicResult.getTopicDetail().setFollowCount(followService.followCount(topicResult.getTopicDetail().getTopic().getId(), TargetType.topic));
				topicResult.setReplyDetail(new ReplyDetail());
				topicResult.getReplyDetail().setReply(relpyService.getHotReplyForRopic(topicResult.getTopicDetail().getTopic().getId()));
				if (topicResult.getReplyDetail().getReply() != null) {
					topicResult.getReplyDetail().setAuthor(userService.getUser(topicResult.getReplyDetail().getReply().getUser_id()));
					topicResult.getReplyDetail().setLikeCount(likeService.likeCount(topicResult.getReplyDetail().getReply().getId(), TargetType.reply));
					topicResult.getReplyDetail().setCommentCount(commentService.commentCount(topicResult.getReplyDetail().getReply().getId()));
				}
			});
			return tagHomeRespone;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return null;
		}
	}
}
