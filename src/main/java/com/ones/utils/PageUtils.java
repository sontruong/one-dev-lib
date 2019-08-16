package com.ones.utils;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.google.gson.Gson;

public class PageUtils<T> {

	public Page<T> getPages(String str, PageRequest pageRequest, Class<T> clazz) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		Gson gson = AppUtils.buildGsonWithLongDate();
		JSONObject jsonObject = new JSONObject(str);
		int total = jsonObject.getInt("totalPages");
		JSONArray jsonArray = jsonObject.getJSONArray("content");
		ArrayList<T> content = new ArrayList<T>();
		if (jsonArray != null) {
			int len = jsonArray.length();
			for (int i = 0; i < len; i++) {
				content.add(gson.fromJson(jsonArray.get(i).toString(), clazz));
			}
		}
		return new PageImpl<>(content, pageRequest, total);
	}
}
