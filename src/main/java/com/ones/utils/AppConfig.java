/**
 * 
 */
package com.ones.utils;

/**
 * @author son.truong
 * Apr 13, 2017 11:03:56 PM
 */
public class AppConfig {

	public static final String APPLICATION_CONTENT_TYPES = "text/plain";
	
	// start id
	public static final String TABLE_ID = "t_ones_id";
	public static final String TABLE_KEY = "t_ones_key";
	public static final String TABLE_VALUE = "t_ones_value";
	public static final int ENTITY_ID_ALLOCATION = 1;
	//end id
	
	public static final int DEFAULT_LIMIT = 10;
	public static final int DEFAULT_PAGE = 0;
	public static final String NONSORT = "NON_SORT";
	public static final String APPLICATION_CONSUMES = "application/json";
	public static final boolean DEFAULT_LITE = false;

	//Mapping
	public static final String API_MAPPING = "api";
	public static final String API_NONAU_MAPPING = API_MAPPING +  "/nonauth";
	public static final String API_ONE_MANAGEMENT = API_MAPPING +  "/mgnt";
	
	// Default value
	public static final String DEFAULT_API_DOWNLOAD = "/download";
	public static final String API_DOWNLOAD = API_NONAU_MAPPING + DEFAULT_API_DOWNLOAD;
	
}
