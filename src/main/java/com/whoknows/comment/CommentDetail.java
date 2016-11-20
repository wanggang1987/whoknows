package com.whoknows.comment;

import com.whoknows.domain.Comment;
import com.whoknows.user.UserDetail;

public class CommentDetail {
	private Comment comment;
	private UserDetail userDtail;
	private Integer likeCount;

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public UserDetail getUserDtail() {
		return userDtail;
	}

	public void setUserDtail(UserDetail userDtail) {
		this.userDtail = userDtail;
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}
}
