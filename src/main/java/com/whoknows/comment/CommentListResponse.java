package com.whoknows.comment;

import com.whoknows.search.Paging;
import java.util.List;

public class CommentListResponse {

	private Paging paging;
	private List<CommentDetail> comments;

	public Paging getPaging() {
		return paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}

	public List<CommentDetail> getComments() {
		return comments;
	}

	public void setComments(List<CommentDetail> comments) {
		this.comments = comments;
	}
}
