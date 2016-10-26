package com.whoknows.search;

import com.whoknows.framework.TopicView;
import com.whoknows.framework.UserView;
import java.util.List;

public class SearchResponse {

	private List<UserView> userViews;
	private List<TopicView> topicViews;

	public List<UserView> getUserViews() {
		return userViews;
	}

	public void setUserViews(List<UserView> userViews) {
		this.userViews = userViews;
	}

	public List<TopicView> getTopicViews() {
		return topicViews;
	}

	public void setTopicViews(List<TopicView> topicViews) {
		this.topicViews = topicViews;
	}

}
