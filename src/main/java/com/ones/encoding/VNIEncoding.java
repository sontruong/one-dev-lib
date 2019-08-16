package com.ones.encoding;

public final class VNIEncoding extends MultipleByteEncoding {
	private static String[] myDD = { "\u00d1", "\u00f1" };

	private static String[] myHND = { "\u0041", "\u0041\u00d8", "\u0041\u00db", "\u0041\u00d5", "\u0041\u00d9",
			"\u0041\u00cf", "\u0041\u00ca", "\u0041\u00c8", "\u0041\u00da", "\u0041\u00dc", "\u0041\u00c9",
			"\u0041\u00cb", "\u0041\u00c2", "\u0041\u00c0", "\u0041\u00c5", "\u0041\u00c3", "\u0041\u00c1",
			"\u0041\u00c4", "\u0045", "\u0045\u00d8", "\u0045\u00db", "\u0045\u00d5", "\u0045\u00d9", "\u0045\u00cf",
			"\u0045\u00c2", "\u0045\u00c0", "\u0045\u00c5", "\u0045\u00c3", "\u0045\u00c1", "\u0045\u00c4", "\u0049",
			"\u00cc", "\u00c6", "\u00d3", "\u00cd", "\u00d2", "\u004f", "\u004f\u00d8", "\u004f\u00db", "\u004f\u00d5",
			"\u004f\u00d9", "\u004f\u00cf", "\u004f\u00c2", "\u004f\u00c0", "\u004f\u00c5", "\u004f\u00c3",
			"\u004f\u00c1", "\u004f\u00c4", "\u00d4", "\u00d4\u00d8", "\u00d4\u00db", "\u00d4\u00d5", "\u00d4\u00d9",
			"\u00d4\u00cf", "\u0055", "\u0055\u00d8", "\u0055\u00db", "\u0055\u00d5", "\u0055\u00d9", "\u0055\u00cf",
			"\u00d6", "\u00d6\u00d8", "\u00d6\u00db", "\u00d6\u00d5", "\u00d6\u00d9", "\u00d6\u00cf", "\u0059",
			"\u0059\u00d8", "\u0059\u00db", "\u0059\u00d5", "\u0059\u00d9", "\u00ce", "\u0061", "\u0061\u00f8",
			"\u0061\u00fb", "\u0061\u00f5", "\u0061\u00f9", "\u0061\u00ef", "\u0061\u00ea", "\u0061\u00e8",
			"\u0061\u00fa", "\u0061\u00fc", "\u0061\u00e9", "\u0061\u00eb", "\u0061\u00e2", "\u0061\u00e0",
			"\u0061\u00e5", "\u0061\u00e3", "\u0061\u00e1", "\u0061\u00e4", "\u0065", "\u0065\u00f8", "\u0065\u00fb",
			"\u0065\u00f5", "\u0065\u00f9", "\u0065\u00ef", "\u0065\u00e2", "\u0065\u00e0", "\u0065\u00e5",
			"\u0065\u00e3", "\u0065\u00e1", "\u0065\u00e4", "\u0069", "\u00ec", "\u00e6", "\u00f3", "\u00ed", "\u00f2",
			"\u006f", "\u006f\u00f8", "\u006f\u00fb", "\u006f\u00f5", "\u006f\u00f9", "\u006f\u00ef", "\u006f\u00e2",
			"\u006f\u00e0", "\u006f\u00e5", "\u006f\u00e3", "\u006f\u00e1", "\u006f\u00e4", "\u00f4", "\u00f4\u00f8",
			"\u00f4\u00fb", "\u00f4\u00f5", "\u00f4\u00f9", "\u00f4\u00ef", "\u0075", "\u0075\u00f8", "\u0075\u00fb",
			"\u0075\u00f5", "\u0075\u00f9", "\u0075\u00ef", "\u00f6", "\u00f6\u00f8", "\u00f6\u00fb", "\u00f6\u00f5",
			"\u00f6\u00f9", "\u00f6\u00ef", "\u0079", "\u0079\u00f8", "\u0079\u00fb", "\u0079\u00f5", "\u0079\u00f9",
			"\u00ee" };

	public static String ID = "VNI";
	private static Encoding instance = new VNIEncoding(myDD, myHND);

	private VNIEncoding(String[] DD, String[] HND) {
		super(DD, HND);
	}

	public String getID() {
		return ID;
	}

	public static Encoding getInstance() {
		return instance;
	}
}
