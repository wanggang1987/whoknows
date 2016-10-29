package com.whoknows.wkMessage.search;

import java.util.List;


public class SearchResponse {

	private String keyWord;
	private Integer currentPage;
	private Integer pageSize;
	private Integer totalPage;
	
	private List<TopicSearchResult> topicResults;
	
}
