package com.whoknows.hot;

public class HotTag {

	private String picture;
	private String tagName;
	private Long tagID;
	private Long follow;

	public Long getFollow() {
		return follow;
	}

	public void setFollow(Long follow) {
		this.follow = follow;
	}

	public Long getTagID() {
		return tagID;
	}

	public void setTagID(Long tagID) {
		this.tagID = tagID;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

}
