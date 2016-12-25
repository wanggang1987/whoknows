package com.whoknows.bi;

public class BiEntry {

	private Integer count;
	private Long target_id;
	private String target_type;

	public void plus(Integer n) {
		if (count == null) {
			count = 0;
		}
		count += n;
	}

	public BiEntry(Integer count, Long target_id, String target_type) {
		this.count = count;
		this.target_id = target_id;
		this.target_type = target_type;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Long getTarget_id() {
		return target_id;
	}

	public void setTarget_id(Long target_id) {
		this.target_id = target_id;
	}

	public String getTarget_type() {
		return target_type;
	}

	public void setTarget_type(String target_type) {
		this.target_type = target_type;
	}
}
