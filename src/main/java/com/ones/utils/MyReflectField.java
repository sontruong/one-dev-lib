package com.ones.utils;

import java.lang.reflect.Field;

public class MyReflectField {
	private String title;
	Field field;
	int index = 0;

	public Field getField() {
		return field;
	}

	public int getIndex() {
		return index;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
