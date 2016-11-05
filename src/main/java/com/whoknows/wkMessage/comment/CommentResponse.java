package com.whoknows.wkMessage.comment;
import java.util.List;

import com.whoknows.wkMessage.common.Paging;

public class CommentResponse {

	private Paging paging ;
	
	private List<CommentResult> data;

	public Paging getPaging() {
		return paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}

	public List<CommentResult> getData() {
		return data;
	}

	public void setData(List<CommentResult> data) {
		this.data = data;
	}
	
}
