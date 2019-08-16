package com.ones.encoding;

public final class VPSEncoding extends SingleByteEncoding
{
	private static char[] myDD = {241,199};
	private static char[] myHND = {
	    65,128,129,130,193,2,136,142,143,240,141,4,
	    194,132,133,28,131,3,69,215,222,254,201,5,
	    202,147,148,149,144,6,73,181,183,184,180,16,
	    79,188,189,190,185,17,212,151,152,153,150,18,
	    247,158,159,166,157,19,85,168,209,172,218,20,
	    208,175,177,29,173,21,89,178,253,179,221,25,
	    97,224,228,227,225,229,230,162,163,164,161,165,
	    226,192,196,197,195,198,101,232,200,235,233,203,
	    234,138,139,205,137,140,105,236,204,239,237,206,
	    111,242,213,245,243,134,244,210,176,135,211,182,
	    214,169,170,171,167,174,117,249,251,219,250,248,
	    220,216,186,187,217,191,121,255,155,207,154,156
	};

	private static Encoding instance = new VPSEncoding(myDD, myHND);
	public static String ID = "VPS";
	/**
 * Insert the method's description here.
 * Creation date: (3/14/00 11:40:45 AM)
 * @param DD char[]
 * @param HND char[]
 */
private VPSEncoding(char[] DD, char[] HND) {
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
