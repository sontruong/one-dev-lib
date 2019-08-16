package com.ones.encoding;

public final class VISCIIEncoding extends SingleByteEncoding
{
	private static char[] myDD = {208,240};
	private static char[] myHND = {
	    65,192,196,195,193,128,197,130,2,5,129,131,
	    194,133,134,6,132,135,69,200,203,136,201,137,
	    202,139,140,141,138,142,73,204,155,206,205,152,
	    79,210,153,160,211,154,212,144,145,146,143,147,
	    180,150,151,179,149,148,85,217,156,157,218,158,
	    191,187,188,255,186,185,89,159,20,25,221,30,
	    97,224,228,227,225,213,229,162,198,199,161,163,
	    226,165,166,231,164,167,101,232,235,168,233,169,
	    234,171,172,173,170,174,105,236,239,238,237,184,
	    111,242,246,245,243,247,244,176,177,178,175,181,
	    189,182,183,222,190,254,117,249,252,251,250,248,
	    223,215,216,230,209,241,121,207,214,219,253,220
	};

	private static Encoding instance = new VISCIIEncoding(myDD, myHND);

	public static String ID = "VISCII";
		/**
 * Insert the method's description here.
 * Creation date: (3/14/00 11:40:45 AM)
 * @param DD char[]
 * @param HND char[]
 */
private VISCIIEncoding(char[] DD, char[] HND) {
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
