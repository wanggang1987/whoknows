package com.whoknows.reply;

import com.whoknows.domain.ActionType;
import com.whoknows.domain.Reply;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelpyService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ReplyRepository replyRepository;

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
}
