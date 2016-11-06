package com.whoknows.search;

import com.whoknows.domain.Topic;
import com.whoknows.user.UserDetail;

public class TopicRusult {

	private Topic topic;
	private UserDetail author;
	private Integer followCount;

	public Integer getFollowCount() {
		return followCount;
	}

	public void setFollowCount(Integer followCount) {
		this.followCount = followCount;
	}

	public UserDetail getAuthor() {
		return author;
	}

	public void setAuthor(UserDetail author) {
		this.author = author;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

}
