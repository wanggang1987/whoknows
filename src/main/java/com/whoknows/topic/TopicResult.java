package com.whoknows.topic;

import com.whoknows.search.TopicRusult;
import com.whoknows.search.ReplyResult;

public class TopicResult {

	private TopicRusult topicDetail;
	private ReplyResult replyDetail;

	public TopicRusult getTopicDetail() {
		return topicDetail;
	}

	public void setTopicDetail(TopicRusult topicDetail) {
		this.topicDetail = topicDetail;
	}

	public ReplyResult getReplyDetail() {
		return replyDetail;
	}

	public void setReplyDetail(ReplyResult replyDetail) {
		this.replyDetail = replyDetail;
	}

}
