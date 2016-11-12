package com.whoknows.hot;

import java.util.List;

public class HotRecommend {

	private List<VipDetail> vips;
	private List<TagDetail> tags;

	public List<VipDetail> getVips() {
		return vips;
	}

	public void setVips(List<VipDetail> vips) {
		this.vips = vips;
	}

	public List<TagDetail> getTags() {
		return tags;
	}

	public void setTags(List<TagDetail> tags) {
		this.tags = tags;
	}
}
