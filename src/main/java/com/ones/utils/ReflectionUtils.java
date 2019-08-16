package com.ones.utils;

import java.lang.reflect.Field;

public class ReflectionUtils {
	public static MyReflectField[] getAllFields(Object obj, boolean subClass) {
		Field[] fields = obj.getClass().getDeclaredFields();
		Field[] fields2 = getAllSubClassFields(obj);
		if (null == fields || 0 == fields.length) {
			return null;
		}
		int length = fields.length;
		if(null != fields2 && 0 < fields2.length) {
			length += fields2.length;
		}
		MyReflectField[] result = new MyReflectField[length];

		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			result[i] = new MyReflectField();
			result[i].setField(field);
		}
		if(null != fields2 && 0 < fields2.length) {
			for (int i = 0; i < fields2.length; i++) {
				Field field = fields2[i];
				result[fields.length + i] = new MyReflectField();
				result[fields.length + i].setField(field);
			}
		}
		return result;
	}
	
	private static Field[] getAllSubClassFields(Object obj) {
		Field[] fields = null;
		try {
			fields = obj.getClass().getSuperclass().getDeclaredFields();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return fields;
	}

	public static Object getFieldValue(Object object, Field field, boolean exception) {
		boolean b = field.isAccessible();
		field.setAccessible(true);
		Object result = null;;
		try {
			result = org.springframework.util.ReflectionUtils.getField(field, object);
		} catch (IllegalArgumentException e) {
			if(exception) {
				throw e;
			}
			e.printStackTrace();
		}
		field.setAccessible(b);
		return result;
	}

	public static void setFieldValue(Object object, Field field, Object value)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		boolean b = field.isAccessible();
		field.setAccessible(true);
		field.set(object, value);
		field.setAccessible(b);
	}
}
