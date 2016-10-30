package com.whoknows.wkMessage.search;

import com.whoknows.wkMessage.user.UserSummaryInfo;
import java.util.List;

public class SearchUserResponse {

	private String keyWord;
	private Integer currentPage;
	private Integer pageSize;
	private Integer totalPage;

	private List<UserSummaryInfo> users;

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public List<UserSummaryInfo> getUsers() {
		return users;
	}

	public void setUsers(List<UserSummaryInfo> users) {
		this.users = users;
	}
}
