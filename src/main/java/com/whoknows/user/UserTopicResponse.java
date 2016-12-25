package com.whoknows.user;

import com.whoknows.search.Paging;
import com.whoknows.search.TopicResult;
import java.util.List;

public class UserTopicResponse {

	private Paging paging;
	private List<TopicResult> topicResults;

	public Paging getPaging() {
		return paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}

	public List<TopicResult> getTopicResults() {
		return topicResults;
	}

	public void setTopicResults(List<TopicResult> topicResults) {
		this.topicResults = topicResults;
	}

}
