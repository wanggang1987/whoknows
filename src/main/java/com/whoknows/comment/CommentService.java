package com.whoknows.comment;

import com.whoknows.domain.ActionType;
import com.whoknows.domain.Comment;
import com.whoknows.domain.TargetType;
import com.whoknows.like.LikeService;
import com.whoknows.search.Paging;
import com.whoknows.user.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final int pageSize = 10;

	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private LikeService likeService;

	public boolean createComment(Comment comment) {
		if (comment.getUser_id() == null
				|| comment.getReply_id() == null
				|| StringUtils.isEmpty(comment.getContent())) {
			return false;
		}
		comment.setAction(ActionType.pending.name());

		try {
			commentRepository.createComment(comment);
			return true;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return false;
		}
	}

	public boolean updateComment(Comment comment) {
		if (comment.getId() == null) {
			return false;
		}

		try {
			commentRepository.updateComment(comment);
			return true;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return false;
		}
	}

	public boolean deleteComment(Long id) {
		if (id == null) {
			return false;
		}

		try {
			commentRepository.deleteComment(id);
			return true;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return false;
		}
	}

	public CommentDetail getComment(Long id) {
		if (id == null) {
			return null;
		}

		try {
			CommentDetail commentDetail = new CommentDetail();
			commentDetail.setComment(commentRepository.getComment(id));
			if (commentDetail.getComment() != null) {
				commentDetail.setUserDtail(userService.getUser(commentDetail.getComment().getUser_id()));
				commentDetail.setLikeCount(likeService.likeCount(commentDetail.getComment().getId(), TargetType.comment));
			}
			return commentDetail;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return null;
		}
	}

	public List<CommentDetail> getCommentDetails(Long replyId, Integer page) {
		if (replyId == null || page == null) {
			return null;
		}

		try {
			return commentRepository.getReplyComments(replyId, page, pageSize).parallelStream().map(id -> getComment(id)).collect(Collectors.toList());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return null;
		}
	}

	public CommentListResponse getCommentListResponse(Long replyId, Integer page) {
		if (replyId == null || page == null) {
			return null;
		}

		try {
			CommentListResponse commentListResponse = new CommentListResponse();
			Paging paging = new Paging();
			paging.setCurrentPage(page);
			paging.setPerPage(pageSize);
			int commentCount = commentRepository.commentCount(replyId) ;
			paging.setTotalPage(commentCount % pageSize == 0 ?  commentCount/ pageSize  : commentCount/ pageSize + 1);
			commentListResponse.setPaging(paging);

			commentListResponse.setComments(commentRepository.getReplyComments(replyId, page, pageSize)
					.parallelStream().map(id -> getComment(id)).collect(Collectors.toList()));
			return commentListResponse;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return null;
		}
	}

	public Integer commentCount(Long reply_id) {
		try {
			return commentRepository.commentCount(reply_id);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return null;
		}
	}
}
