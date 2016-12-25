package com.whoknows.search;

import com.whoknows.reply.ReplyDetail;
import com.whoknows.topic.TopicDetail;

public class TopicResult {

	private TopicDetail topicDetail;
	private ReplyDetail replyDetail;

	public TopicDetail getTopicDetail() {
		return topicDetail;
	}

	public void setTopicDetail(TopicDetail topicDetail) {
		this.topicDetail = topicDetail;
	}

	public ReplyDetail getReplyDetail() {
		return replyDetail;
	}

	public void setReplyDetail(ReplyDetail replyDetail) {
		this.replyDetail = replyDetail;
	}

}
