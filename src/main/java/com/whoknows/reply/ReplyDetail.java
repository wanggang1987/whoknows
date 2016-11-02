package com.whoknows.reply;

import com.whoknows.domain.Reply;
import com.whoknows.wkMessage.user.UserSummaryInfo;

public class ReplyDetail {

	private Reply reply;
	private UserSummaryInfo user;
	private UserSummaryInfo user_replyed;
	
	private Integer like;
	private Integer follow;
	private Integer commet;

	public Integer getLike() {
		return like;
	}

	public void setLike(Integer like) {
		this.like = like;
	}

	public Integer getFollow() {
		return follow;
	}

	public void setFollow(Integer follow) {
		this.follow = follow;
	}

	public Integer getCommet() {
		return commet;
	}

	public void setCommet(Integer commet) {
		this.commet = commet;
	}

	public Reply getReply() {
		return reply;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
	}

	public UserSummaryInfo getUser() {
		return user;
	}

	public void setUser(UserSummaryInfo user) {
		this.user = user;
	}

	public UserSummaryInfo getUser_replyed() {
		return user_replyed;
	}

	public void setUser_replyed(UserSummaryInfo user_replyed) {
		this.user_replyed = user_replyed;
	}
}
