package com.whoknows.message.search;

import com.whoknows.domain.Reply;
import com.whoknows.domain.Topic;
import com.whoknows.message.user.UserSummaryInfo;

public class TopicResult {

	private Topic topic;
	private UserSummaryInfo topicUser;
	
	private Reply reply;
	private UserSummaryInfo replyUser;
	
	private Integer replyFollowCount;
	private Integer replyLikeCount;
	private Integer replyCommentCount;
	
	public Topic getTopic() {
		return topic;	
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	public UserSummaryInfo getTopicUser() {
		return topicUser;
	}
	public void setTopicUser(UserSummaryInfo topicUser) {
		this.topicUser = topicUser;
	}
	public Reply getReply() {
		return reply;
	}
	public void setReply(Reply reply) {
		this.reply = reply;
	}
	public UserSummaryInfo getReplyUser() {
		return replyUser;
	}
	public void setReplyUser(UserSummaryInfo replyUser) {
		this.replyUser = replyUser;
	}
	public Integer getReplyFollowCount() {
		return replyFollowCount;
	}
	public void setReplyFollowCount(Integer replyFollowCount) {
		this.replyFollowCount = replyFollowCount;
	}
	public Integer getReplyLikeCount() {
		return replyLikeCount;
	}
	public void setReplyLikeCount(Integer replyLikeCount) {
		this.replyLikeCount = replyLikeCount;
	}
	public Integer getReplyCommentCount() {
		return replyCommentCount;
	}
	public void setReplyCommentCount(Integer replyCommentCount) {
		this.replyCommentCount = replyCommentCount;
	}
}
