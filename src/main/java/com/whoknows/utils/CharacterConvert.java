package com.whoknows.utils;

import org.apache.commons.lang.StringUtils;

public class CharacterConvert {

	public static String translateSqlConvert(String str){
		if(StringUtils.isEmpty(str)){
			return "";
		}
		return str.replaceAll("'", "\'");
	}
}
