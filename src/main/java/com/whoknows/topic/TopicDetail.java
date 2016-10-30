package com.whoknows.topic;

import com.whoknows.domain.Topic;
import com.whoknows.wkMessage.user.UserSummaryInfo;

public class TopicDetail {

	private Topic topic;
	private UserSummaryInfo user;

	public UserSummaryInfo getUser() {
		return user;
	}

	public void setUser(UserSummaryInfo user) {
		this.user = user;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

}
