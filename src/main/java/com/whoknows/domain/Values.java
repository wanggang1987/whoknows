/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whoknows.domain;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class Values {

	public static Map<String, Object> getValseMap(Object object) {
		Map<String, Object> map = new HashMap<>();
		Field[] fields = object.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			String name = field.getName();
			try {
				Object value = field.get(object);
				if (name.equals("serialVersionUID")
						|| value == null
						|| !isRightType(value.getClass())) {
					continue;
				}

				if (value.getClass() == Enum.class) {
					map.put(name, value.toString());
				} else {
					map.put(name, value);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	private static boolean isRightType(Class objectClass) {
		if (objectClass == boolean.class || objectClass == Boolean.class
				|| objectClass == short.class || objectClass == Short.class
				|| objectClass == byte.class || objectClass == Byte.class
				|| objectClass == int.class || objectClass == Integer.class
				|| objectClass == long.class || objectClass == Long.class
				|| objectClass == float.class || objectClass == Float.class
				|| objectClass == char.class || objectClass == Character.class
				|| objectClass == double.class || objectClass == Double.class
				|| objectClass == String.class
				|| objectClass == Timestamp.class
				|| objectClass == BigDecimal.class) {
			return true;
		} else {
			return false;
		}
	}
}
