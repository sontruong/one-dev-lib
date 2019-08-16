package com.ones.encoding;

public abstract class AbstractEncoding implements Encoding {
	protected static char[] TCVN1_DD = { 167, 174 };

	protected static char[] TCVN1_HND = { 65, 128, 129, 130, 131, 132, 161, 175, 186, 191, 192, 133, 162, 193, 194, 195,
			196, 134, 69, 135, 136, 137, 138, 139, 163, 197, 205, 217, 218, 140, 73, 141, 142, 143, 144, 145, 79, 146,
			147, 148, 149, 150, 164, 219, 224, 240, 255, 151, 165, 152, 153, 154, 155, 156, 85, 157, 158, 159, 1, 2,
			166, 4, 5, 6, 17, 18, 89, 19, 20, 21, 22, 23, 97, 181, 182, 183, 184, 185, 168, 187, 188, 189, 190, 198,
			169, 199, 200, 201, 202, 203, 101, 204, 206, 207, 208, 209, 170, 210, 211, 212, 213, 214, 105, 215, 216,
			220, 221, 222, 111, 223, 225, 226, 227, 228, 171, 229, 230, 231, 232, 233, 172, 234, 235, 236, 237, 238,
			117, 239, 241, 242, 243, 244, 173, 245, 246, 247, 248, 249, 121, 250, 251, 252, 253, 254 };

	private static int[] Unicode2TCVN1;

	public String fromUnicode(String str) {
		int[] tab = getUnicode2TCVN1();
		char[] b = new char[str.length()];
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (ch < tab.length && tab[ch] >= 0) {
				b[i] = (char) tab[ch];
			} else {
				b[i] = '?';
			}
		}
		return this.fromTCVN1(new String(b));
	}

	public String getDescription() {
		return getID();
	}

	public String getID() {
		return null;
	}

	private static int[] getUnicode2TCVN1() {
		if (Unicode2TCVN1 == null) {
			Unicode2TCVN1 = initCharToByte(TCVN1);
		}
		return Unicode2TCVN1;
	}

	private static int[] initCharToByte(int[] tab) {
		int max = 0;
		for (int i = 0; i < tab.length; i++) {
			if (max < tab[i]) {
				max = tab[i];
			}
		}
		int[] ret = new int[max + 1];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = -1;
		}
		for (int i = 0; i < tab.length; i++) {
			ret[tab[i]] = i;
		}
		return ret;
	}

	/**
	 * isLowerCase method comment.
	 */
	public boolean isLowerCase(char c) {
		return (c == this.toLowerCase(c));
	}

	/**
	 * isUpperCase method comment.
	 */
	public boolean isUpperCase(char c) {
		return (c == this.toUpperCase(c));
	}

	/**
	 * toLowerCase method comment.
	 */
	public char toLowerCase(char c) {
		return Character.toLowerCase(c);
	}

	/**
	 * toLowerCase method comment.
	 */
	public java.lang.String toLowerCase(java.lang.String str) {
		char[] data = new char[str.length()];
		for (int i = 0; i < str.length(); i++) {
			data[i] = this.toLowerCase(str.charAt(i));
		}
		return new String(data);
	}

	public String toString() {
		return getID();
	}

	public String toUnicode(String str) {
		String s = this.toTCVN1(str);
		char[] b = new char[s.length()];
		for (int i = 0; i < s.length(); i++) {
			b[i] = (char) TCVN1[s.charAt(i) & 0xff];
		}
		return new String(b);
	}

	/**
	 * toUpperCase method comment.
	 */
	public char toUpperCase(char c) {
		return Character.toUpperCase(c);
	}

	/**
	 * toUpperCase method comment.
	 */
	public java.lang.String toUpperCase(java.lang.String str) {
		char[] data = new char[str.length()];
		for (int i = 0; i < str.length(); i++) {
			data[i] = this.toUpperCase(str.charAt(i));
		}
		return new String(data);
	}
}
