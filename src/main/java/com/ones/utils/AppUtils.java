/**
 * 
 */
package com.ones.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.ones.dto.SortDTO;

/**
 * @author son.truong Apr 13, 2017 11:03:56 PM
 */
public class AppUtils {

	public static void printStackTrace(Exception ex, Logger logger) {
		try {
			StringWriter sw = new StringWriter();
			ex.printStackTrace(new PrintWriter(sw));
			logger.error(sw.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Sort createSort(Direction direction, String property) {
		if (StringUtils.isNotEmpty(property) && !property.equalsIgnoreCase(AppConfig.NONSORT)) {
			if (null == direction) {
				direction = Direction.ASC;
			}
			Order order = new Order(direction, property);
			Sort sort = new Sort(order);
			return sort;
		}
		return null;
	}

	public static Sort createSort(SortDTO... sortDTOs) {
		if (null == sortDTOs || 0 == sortDTOs.length) {
			return null;
		}

		List<Order> orders = new ArrayList<>();

		for (SortDTO sortDTO : sortDTOs) {
			String property = sortDTO.getProperty();
			Direction direction = sortDTO.getDirection();

			if (StringUtils.isNotEmpty(property) && !property.equalsIgnoreCase(AppConfig.NONSORT)) {
				if (null == direction) {
					direction = Direction.ASC;
				}
				Order order = new Order(direction, property);
				orders.add(order);
			}
		}

		Sort sort = new Sort(orders);
		return sort;
	}

	public static Sort createAvailableSort(Object object, SortDTO... sortDTOs) {
		if (null == sortDTOs || 0 == sortDTOs.length) {
			return null;
		}

		MyReflectField[] fields = ReflectionUtils.getAllFields(object, false);
		if (null == fields || 0 == fields.length) {
			return null;
		}

		List<Order> orders = new ArrayList<>();
		for (SortDTO sortDTO : sortDTOs) {
			String property = sortDTO.getProperty();
			Direction direction = sortDTO.getDirection();

			if (StringUtils.isNotEmpty(property) && !property.equalsIgnoreCase(AppConfig.NONSORT)
					&& checkExistField(property, fields)) {
				if (null == direction) {
					direction = Direction.ASC;
				}
				Order order = new Order(direction, property);
				orders.add(order);
			}
		}
		if (0 == orders.size()) {
			return null;
		}
		Sort sort = new Sort(orders);
		return sort;
	}

	private static boolean checkExistField(String property, MyReflectField[] fields) {
		for (MyReflectField field : fields) {
			if (field.getField().getName().equals(property)) {
				return true;
			}
		}
		return false;
	}

	public static PageRequest createPageRequest(Integer page, Integer limit, Sort sort) {

		PageRequest pageRequest = null;

		if (null == page || 0 > page) {
			page = 0;
		}
		if (null == limit || 1 > limit) {
			limit = AppConfig.DEFAULT_LIMIT;
		}

		if (null == sort) {
			pageRequest = new PageRequest(page, limit);
		} else {
			pageRequest = new PageRequest(page, limit, sort);
		}

		return pageRequest;
	}

	public static Gson buildGsonWithLongDate() {
		// Creates the json object which will manage the information received
		GsonBuilder builder = new GsonBuilder();

		// Register an adapter to manage the date types as long values
		builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
			public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				return new Date(json.getAsJsonPrimitive().getAsLong());
			}
		});

		Gson gson = builder.create();
		return gson;
	}

	public static Gson buildGsonWithCustomDateFormat(String format) {
		Gson gson = new GsonBuilder().setDateFormat(format).create();

		return gson;
	}

	public static String randomIntString(int max) {
		String result = "";
		if (1 > max) {
			return result;
		}
		Random random = new Random();
		for (int i = 0; i < max; i++) {
			int num = random.nextInt(10);
			result += num;
		}
		return result;
	}
	
	public static String fixStringLength(Long num, int max) {
		String result = "" + num;
		if (result.length() > max) {
			result = result.substring(0, max);
		}
		if (result.length() < max) {
			while (result.length() < max) {
				result = "0" + result;
			}
		}

		return result;
	}

	public static String fixStringLength(int num, int max) {
		String result = "" + num;
		if (result.length() > max) {
			result = result.substring(0, max);
		}
		if (result.length() < max) {
			while (result.length() < max) {
				result = "0" + result;
			}
		}

		return result;
	}
}
