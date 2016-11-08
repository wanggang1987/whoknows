package com.whoknows.user;

public class UserConutInfoResponse {

	private Integer followCount ;
	private Integer createTopicCount;
	private Integer replyCount;
	
	public Integer getFollowCount() {
		return followCount;
	}
	public void setFollowCount(Integer followCount) {
		this.followCount = followCount;
	}
	public Integer getCreateTopicCount() {
		return createTopicCount;
	}
	public void setCreateTopicCount(Integer createTopicCount) {
		this.createTopicCount = createTopicCount;
	}
	public Integer getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}
}
