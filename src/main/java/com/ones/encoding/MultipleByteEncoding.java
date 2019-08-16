package com.ones.encoding;

import java.util.Hashtable;

public abstract class MultipleByteEncoding extends AbstractEncoding {
	private String[] DD = new String[2];
	private String[] HND = new String[144];
	private java.lang.String[] FROM_TCVN1;
	private java.util.Hashtable TO_TCVN1;

	protected MultipleByteEncoding(String[] DD, String[] HND) {
		super();
		this.DD = DD;
		this.HND = HND;
		initialize();
	}

	public char addBreveHorn(char c) {
		return c;
	}

	public char addCircumflex(char c) {
		return c;
	}

	public char addStroke(char c) {
		return c;
	}

	public static String convert(char[] arr, Hashtable d, int max) {
		StringBuffer sb = new StringBuffer(arr.length);
		String key = "";
		boolean found;
		int i = 0;
		int k;
		while (i < arr.length - max + 1) {
			found = false;
			k = max;
			while (k > 0 && !found) {
				key = new String(arr, i, k);
				if (d.containsKey(key))
					found = true;
				else
					k--;
			}
			if (found) {
				sb.append(d.get(key));
				for (int j = 0; j < k; j++) {
					i++;
				}
			} else {
				sb.append(arr[i]);
				i++;
			}
		}
		return sb.toString();
	}

	public static String convert(String str, Hashtable d, int max) {
		String s = str;
		for (int i = 0; i < max - 1; i++) {
			s = s + " ";
		}
		return convert(s.toCharArray(), d, max);
	}

	/**
	 * fromTCVN1 method comment.
	 */
	public java.lang.String fromTCVN1(java.lang.String str) {
		String[] table = getFROM_TCVN1();
		StringBuffer sb = new StringBuffer(str.length());
		for (int i = 0; i < str.length(); i++) {
			sb.append(table[str.charAt(i) & 0xff]);
		}
		return sb.toString();
	}

	/**
	 * Insert the method's description here. Creation date: (3/16/00 2:30:17 PM)
	 * 
	 * @return String[]
	 */
	protected String[] getFROM_TCVN1() {
		if (FROM_TCVN1 == null) {
			initFROM_TCVN1();
		}
		return FROM_TCVN1;
	}

	protected int getMaxBytes() {
		return 2;
	}

	/**
	 * Insert the method's description here. Creation date: (3/16/00 2:17:32 PM)
	 * 
	 * @return java.util.Hashtable
	 */
	protected java.util.Hashtable getTO_TCVN1() {
		if (TO_TCVN1 == null) {
			initTO_TCVN1();
		}
		return TO_TCVN1;
	}

	public boolean hasDiacritic(char c) {
		return false;
	}

	/**
	 * Insert the method's description here. Creation date: (3/16/00 2:17:32 PM)
	 */
	private void initFROM_TCVN1() {
		int size = 256;
		FROM_TCVN1 = new String[size];
		for (int i = 0; i < size; i++) {
			FROM_TCVN1[i] = String.valueOf((char) i);
		}
		for (int i = 0; i < DD.length; i++) {
			FROM_TCVN1[TCVN1_DD[i]] = DD[i];
		}
		for (int i = 0; i < HND.length; i++) {
			FROM_TCVN1[TCVN1_HND[i]] = HND[i];
		}
	}

	protected void initialize() {
	}

	/**
	 * Insert the method's description here. Creation date: (3/16/00 2:17:32 PM)
	 */
	protected void initTO_TCVN1() {
		TO_TCVN1 = new java.util.Hashtable(307);
		for (int i = 0; i < DD.length; i++) {
			TO_TCVN1.put(DD[i], new Character(TCVN1_DD[i]));
		}
		for (int i = 0; i < HND.length; i++) {
			TO_TCVN1.put(HND[i], new Character(TCVN1_HND[i]));
		}
	}

	public boolean isLetter(char c) {
		return Character.isLetter(c);
	}

	public boolean isVowel(char c) {
		return false;
	}

	public char modifyTone(char original, int toneMark) {
		return original;
	}

	/**
	 * toTCVN1 method comment.
	 */
	public java.lang.String toTCVN1(java.lang.String str) {
		return convert(str, getTO_TCVN1(), getMaxBytes());
	}
}
