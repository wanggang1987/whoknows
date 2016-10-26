package com.whoknows.search;

import com.whoknows.domain.Topic;
import com.whoknows.domain.User;
import java.util.List;

public class SearchResponse {

	private List<User> user;
	private List<Topic> topics;

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}
}
