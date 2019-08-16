package com.ones.encoding;

/**
 * 8-bit encoding VN3. Contains all lower case and some upper case Vietnamese characters.
 * No control character used.
 */
 
public final class TCVN3Encoding extends SingleByteEncoding
{
	private static char[] myDD = {167,174};
	private static char[] myHND = {
	65,181,182,183,184,185,161,187,188,189,190,198,
	162,199,200,201,202,203,69,204,206,207,208,209,
	163,210,211,212,213,214,73,215,216,220,221,222,
	79,223,225,226,227,228,164,229,230,231,232,233,
	165,234,235,236,237,238,85,239,241,242,243,244,
	166,245,246,247,248,249,89,250,251,252,253,254,
	97,181,182,183,184,185,168,187,188,189,190,198,
	169,199,200,201,202,203,101,204,206,207,208,209,
	170,210,211,212,213,214,105,215,216,220,221,222,
	111,223,225,226,227,228,171,229,230,231,232,233,
	172,234,235,236,237,238,117,239,241,242,243,244,
	173,245,246,247,248,249,121,250,251,252,253,254
	};

	private static Encoding instance = new TCVN3Encoding(myDD, myHND);

	public static String ID = "TCVN3";
/**
 * Insert the method's description here.
 * Creation date: (3/14/00 11:40:45 AM)
 * @param DD char[]
 * @param HND char[]
 */
private TCVN3Encoding(char[] DD, char[] HND) {
	super(DD, HND);
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
}
