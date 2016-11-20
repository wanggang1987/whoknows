package com.whoknows.mail;

import org.apache.commons.lang.StringUtils;

public class RegisterMailInfo {

	private String toAddress;
	private String title;
	private String content;

	public boolean validate() {
		if (StringUtils.isEmpty(toAddress)
				|| StringUtils.isEmpty(title)
				|| StringUtils.isEmpty(content)) {
			return false;
		}
		return true;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
