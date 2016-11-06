package com.whoknows.message.topic;

import java.util.List;

public class CreateTopicRequest {

	private Integer userId;
	private String content;
	private String title;
	private List<Integer> tagId;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Integer> getTagId() {
		return tagId;
	}
	public void setTagId(List<Integer> tagId) {
		this.tagId = tagId;
	}
}
