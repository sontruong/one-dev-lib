/**
 * 
 */
package com.ones.utils;

/**
 * @author son.truong
 * Jul 22, 2018
 */
public class NumberUtils {
	public static boolean checkIdNumber(Long id) {
		if (null != id && 0 < id) {
			return true;
		}
		return false;
	}
	
	public static Double getDouble(Double d) {
		if (null == d) {
			d = 0D;
		}
		return d;
	}
	
	public static Double addDouble(Double d, Double d2) {
		if (null == d) {
			d = 0D;
		}
		return d + d2;
	}
	
	public static Double subtractDouble(Double d, Double d2) {
		if (null == d) {
			d = 0D;
		}
		return d - d2;
	}
}
