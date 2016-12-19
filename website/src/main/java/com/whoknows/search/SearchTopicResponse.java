package com.whoknows.search;

import java.util.List;

public class SearchTopicResponse {

	private String keyWord;
	private Paging paging;
	private List<TopicResult> topicResults;

	public Paging getPaging() {
		return paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public List<TopicResult> getTopicResults() {
		return topicResults;
	}

	public void setTopicResults(List<TopicResult> topicResults) {
		this.topicResults = topicResults;
	}

}
