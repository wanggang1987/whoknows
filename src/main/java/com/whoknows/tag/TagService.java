package com.whoknows.tag;

import com.whoknows.comment.CommentService;
import com.whoknows.domain.ActionType;
import com.whoknows.domain.Reply;
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

			tagRepository.getTopicByTag(tagId, page, pageSize).parallelStream().forEach(topic -> {
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
				tagHomeRespone.getTopicResults().add(topicResult);
			});
			return tagHomeRespone;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return null;
		}
	}

	public Tag getTagByID(Long tagId) {
		try {
			return tagRepository.getTag(tagId);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return null;
		}
		
	}
}
