package com.whoknows.paper;

import com.whoknows.domain.Paper;
import com.whoknows.search.ShortContent;
import com.whoknows.user.UserDetail;

public class PaperDetail {

	private Paper paper;
	private UserDetail author;
	private Integer likeCount;
	private Boolean currentLiked;
	private ShortContent shortContent;

	public ShortContent getShortContent() {
		return shortContent;
	}

	public void setShortContent(ShortContent shortContent) {
		this.shortContent = shortContent;
	}

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	public UserDetail getAuthor() {
		return author;
	}

	public void setAuthor(UserDetail author) {
		this.author = author;
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public Boolean getCurrentLiked() {
		return currentLiked;
	}

	public void setCurrentLiked(Boolean currentLiked) {
		this.currentLiked = currentLiked;
	}

}
