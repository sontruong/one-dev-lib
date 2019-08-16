/**
 * 
 */
package com.ones.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author son.truong 
 * Jul 12, 2018
 */
public class DateUtils {
	public static Date getStartTimeOfDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 1);
		date = calendar.getTime();

		return date;
	}

	public static Date getEndTimeOfDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		date = calendar.getTime();

		return date;
	}
}
