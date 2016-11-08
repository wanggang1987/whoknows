package com.whoknows.hot;

public class HotVip {

	private String pricture;
	private String name;
	private Long userID;
	private Long vipID;
	private Integer followCount;
	private Boolean currentFollowed;

	public Integer getFollowCount() {
		return followCount;
	}

	public void setFollowCount(Integer followCount) {
		this.followCount = followCount;
	}

	public Boolean getCurrentFollowed() {
		return currentFollowed;
	}

	public void setCurrentFollowed(Boolean currentFollowed) {
		this.currentFollowed = currentFollowed;
	}

	public String getPricture() {
		return pricture;
	}

	public void setPricture(String pricture) {
		this.pricture = pricture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public Long getVipID() {
		return vipID;
	}

	public void setVipID(Long vipID) {
		this.vipID = vipID;
	}
}
