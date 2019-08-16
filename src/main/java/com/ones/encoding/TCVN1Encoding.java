package com.ones.encoding;

/**
 * 8-bit encoding VN1. Contains all 134 Vietnamese characters.
 * Part of the control area (0-31) is used.
 * Concretely:
 * <pre>
 * capital.u.acute=1
 * capital.u.dot.below=2
 * capital.u.horn.grave=4
 * capital.u.horn.hook.above=5
 * capital.u.horn.tilde=6
 * capital.u.horn.acute=17
 * capital.u.horn.dot.below=18
 * capital.y.grave=19
 * capital.y.hook.above=20
 * capital.y.tilde=21
 * capital.y.acute=22
 * capital.y.dot.below=23
 * </pre>
 */
 
public final class TCVN1Encoding extends SingleByteEncoding
{
	
	private static char[] myDD = TCVN1_DD;

	private static char[] myHND = TCVN1_HND;

	private static Encoding instance = new TCVN1Encoding(myDD, myHND);
	public static String ID = "TCVN1";
	
	/**
 * Insert the method's description here.
 * Creation date: (3/14/00 11:40:45 AM)
 * @param DD char[]
 * @param HND char[]
 */
private TCVN1Encoding(char[] DD, char[] HND) {
	super(DD, HND);
}
/**
 * fromTCVN1 method comment.
 */
public java.lang.String fromTCVN1(java.lang.String str) {
	return str;
}
	public String getDescription() {
		return "8-bit encoding 1993:5712-VN1";
	}
	public String getID() { return ID; }
/**
 * Insert the method's description here.
 * Creation date: (2/28/00 3:13:12 PM)
 * @return duc.util.Encoding
 */
public static Encoding getInstance() {
	return instance;
}
/**
 * toTCVN1 method comment.
 */
public java.lang.String toTCVN1(java.lang.String str) {
	return str;
}
}
