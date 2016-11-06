package com.whoknows.comment;

import com.whoknows.domain.ActionType;
import com.whoknows.domain.Comment;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CommentRepository commentRepository;

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

	public Comment getComment(Long id) {
		if (id == null) {
			return null;
		}

		try {
			return commentRepository.getComment(id);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return null;
		}
	}
}
