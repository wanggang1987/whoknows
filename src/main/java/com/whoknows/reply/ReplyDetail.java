package com.whoknows.reply;

import com.whoknows.domain.Reply;
import com.whoknows.search.ShortContent;
import com.whoknows.user.UserDetail;

public class ReplyDetail {

	private Reply reply;
	private UserDetail author;
	private Integer likeCount;
	private Integer commentCount;
	private Boolean currentLiked;
	private ShortContent shortContent;

	public ShortContent getShortContent() {
		return shortContent;
	}

	public void setShortContent(ShortContent shortContent) {
		this.shortContent = shortContent;
	}

	public Boolean getCurrentLiked() {
		return currentLiked;
	}

	public void setCurrentLiked(Boolean currentLiked) {
		this.currentLiked = currentLiked;
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Reply getReply() {
		return reply;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
	}

	public UserDetail getAuthor() {
		return author;
	}

	public void setAuthor(UserDetail author) {
		this.author = author;
	}
}
