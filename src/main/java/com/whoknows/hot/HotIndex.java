package com.whoknows.hot;

import java.util.List;

public class HotIndex {
	private List<HotVip>  vips;
	private List<HotTag> tags;

	public List<HotVip> getVips() {
		return vips;
	}

	public void setVips(List<HotVip> vips) {
		this.vips = vips;
	}

	public List<HotTag> getTags() {
		return tags;
	}

	public void setTags(List<HotTag> tags) {
		this.tags = tags;
	}
}
