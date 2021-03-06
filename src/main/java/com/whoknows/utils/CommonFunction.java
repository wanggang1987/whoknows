package com.whoknows.utils;

import com.whoknows.search.ShortContent;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CommonFunction {

	private static final int shortTextLimit = 200;

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
		return HtmlProcessor.getHighlightedHtml(key, html);
	}

	public static ShortContent shortText(String html) {
		return HtmlProcessor.getShortText(html, shortTextLimit);
	}

	public static ShortContent highLightShortText(String key, String html) {
		return HtmlProcessor.getHighLightShortText(key, html, shortTextLimit);
	}
}
