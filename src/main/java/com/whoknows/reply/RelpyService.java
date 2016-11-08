package com.whoknows.reply;

import com.whoknows.comment.CommentService;
import com.whoknows.domain.ActionType;
import com.whoknows.domain.Reply;
import com.whoknows.domain.TargetType;
import com.whoknows.like.LikeService;
import com.whoknows.user.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelpyService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final int pageSize = 10;

	@Autowired
	private ReplyRepository replyRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private LikeService likeService;
	@Autowired
	private CommentService commentService;

	public Reply getHotReplyForRopic(Long topicId) {
		if (topicId == null) {
			return null;
		}

		try {
			return replyRepository.getHotReplyForRopic(topicId);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return null;
		}
	}

	public boolean createReply(Reply reply) {
		if (reply.getUser_id() == null
				|| reply.getTopic_id() == null
				|| StringUtils.isEmpty(reply.getContent())) {
			return false;
		}

		reply.setAction(ActionType.pending.toString());
		try {
			replyRepository.createReply(reply);
			return true;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return false;
		}
	}

	public boolean updateReply(Reply reply) {
		if (reply.getId() == null
				|| StringUtils.isEmpty(reply.getContent())) {
			return false;
		}

		try {
			replyRepository.updateReply(reply);
			return true;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return false;
		}
	}

	public boolean deleteReply(Long id) {
		if (id == null) {
			return false;
		}

		try {
			replyRepository.deleteReply(id);
			return true;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return false;
		}
	}

	public Reply getReply(Long id) {
		if (id == null) {
			return null;
		}

		try {
			return replyRepository.getReply(id);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return null;
		}
	}

	public ReplyDetail getReplyDetail(Long id) {
		try {
			ReplyDetail replyDetail = new ReplyDetail();
			replyDetail.setReply(replyRepository.getReply(id));
			if (replyDetail.getReply() != null) {
				replyDetail.setAuthor(userService.getUser(replyDetail.getReply().getUser_id()));
				replyDetail.setLikeCount(likeService.likeCount(replyDetail.getReply().getId(), TargetType.reply));
				replyDetail.setCommentCount(commentService.commentCount(replyDetail.getReply().getId()));
			}
			return replyDetail;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return null;
		}
	}

	public List<ReplyDetail> getReplyDetails(Long topicId, Integer page) {
		if (topicId ==null || page == null) {
			return null;
		}
		
		try {
			return replyRepository.getTopicReplys(topicId, page, pageSize).parallelStream().map(id -> 
					getReplyDetail(id)).collect(Collectors.toList());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return null;
		}
	}
}
