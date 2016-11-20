package com.whoknows.tag;

import com.whoknows.domain.Tag;
import com.whoknows.search.Paging;
import com.whoknows.search.TopicResult;
import java.util.List;

public class TagHomeRespone {

	private Tag tag;
	private Paging paging;
	private Integer tagFollowCount;
	private Boolean currentFollowed;
	private List<TopicResult> topicResults;

	public Boolean getCurrentFollowed() {
		return currentFollowed;
	}

	public void setCurrentFollowed(Boolean currentFollowed) {
		this.currentFollowed = currentFollowed;
	}

	public Integer getTagFollowCount() {
		return tagFollowCount;
	}

	public void setTagFollowCount(Integer tagFollowCount) {
		this.tagFollowCount = tagFollowCount;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

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
