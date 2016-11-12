package com.whoknows.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CommonFunction {

	public static String getUserName(String first_name, String last_name, String email) {
		String name = new String();
		if (StringUtils.isNotEmpty(last_name)) {
			name += last_name;
		}
		if (StringUtils.isNotEmpty(first_name)) {
			name += first_name;
		}
		if (StringUtils.isEmpty(name)) {
			name = email;
		}
		return name;
	}

	public static String highLight(String key, String html) {
		return Highlighter.getHighlightedHtml(key, html);
	}
}
