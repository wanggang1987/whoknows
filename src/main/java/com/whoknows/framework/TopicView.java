package com.whoknows.framework;

import com.whoknows.domain.Reply;
import com.whoknows.domain.Topic;

public class TopicView {

	private Topic topic;
	private Reply reply;

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public Reply getReply() {
		return reply;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
	}
}
