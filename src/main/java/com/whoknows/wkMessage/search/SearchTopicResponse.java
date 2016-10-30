package com.whoknows.wkMessage.search;

import java.util.List;


public class SearchTopicResponse {

	private String keyWord;
	private Integer currentPage;
	private Integer pageSize;
	private Integer totalPage;
	
	private List<TopicResult> topicResults;

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public List<TopicResult> getTopicResults() {
		return topicResults;
	}

	public void setTopicResults(List<TopicResult> topicResults) {
		this.topicResults = topicResults;
	}
	
}
