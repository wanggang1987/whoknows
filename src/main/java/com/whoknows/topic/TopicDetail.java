package com.whoknows.topic;

import com.whoknows.domain.Topic;
import com.whoknows.reply.ReplyDetail;
import com.whoknows.user.UserDetail;
import java.util.List;

public class TopicDetail {

	private Topic topic;
	private UserDetail author;
	private Integer followCount;
	private Boolean currentFollowed;
	private List<ReplyDetail> replys;

	public Boolean getCurrentFollowed() {
		return currentFollowed;
	}

	public void setCurrentFollowed(Boolean currentFollowed) {
		this.currentFollowed = currentFollowed;
	}

	public List<ReplyDetail> getReplys() {
		return replys;
	}

	public void setReplys(List<ReplyDetail> replys) {
		this.replys = replys;
	}

	public Integer getFollowCount() {
		return followCount;
	}

	public void setFollowCount(Integer followCount) {
		this.followCount = followCount;
	}

	public UserDetail getAuthor() {
		return author;
	}

	public void setAuthor(UserDetail author) {
		this.author = author;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

}
