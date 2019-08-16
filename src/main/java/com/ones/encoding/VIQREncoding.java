package com.ones.encoding;

public final class VIQREncoding extends MultipleByteEncoding
{
	private static String[] myDD = {"Dd", "dd"};

	private static String[] myHND = {
	"A","A`","A?","A~","A'","A.","A(","A(`","A(?","A(~","A('","A(.",
	"A^","A^`","A^?","A^~","A^'","A^.","E","E`","E?","E~","E'","E.",
	"E^","E^`","E^?","E^~","E^'","E^.","I","I`","I?","I~","I'","I.",
	"O","O`","O?","O~","O'","O.","O^","O^`","O^?","O^~","O^'","O^.",
	"O+","O+`","O+?","O+~","O+'","O+.","U","U`","U?","U~","U'","U.",
	"U+","U+`","U+?","U+~","U+'","U+.","Y","Y`","Y?","Y~","Y'","Y.",
	"a","a`","a?","a~","a'","a.","a(","a(`","a(?","a(~","a('","a(.",
	"a^","a^`","a^?","a^~","a^'","a^.","e","e`","e?","e~","e'","e.",
	"e^","e^`","e^?","e^~","e^'","e^.","i","i`","i?","i~","i'","i.",
	"o","o`","o?","o~","o'","o.","o^","o^`","o^?","o^~","o^'","o^.",
	"o+","o+`","o+?","o+~","o+'","o+.","u","u`","u?","u~","u'","u.",
	"u+","u+`","u+?","u+~","u+'","u+.","y","y`","y?","y~","y'","y."
	};

	public static String ID = "VIQR";
	private static Encoding instance = new VIQREncoding(myDD, myHND);
	private VIQREncoding(String[] DD, String[] HND) {
		super(DD, HND);
	}
private char compose(Encoding e, char orig, char modifier) {
	switch (modifier) {
		case 'd' :
		case 'D' :
			{
				return e.addStroke(orig);
			}
		case '^' :
			{
				return e.addCircumflex(orig);
			}
		case '(' :
		case '<' :
		case '+' :
		case '*' :
			{
				return e.addBreveHorn(orig);
			}
		case '`' :
			{
				return e.modifyTone(orig, Encoding.HUYEN);
			}
		case '?' :
			{
				return e.modifyTone(orig, Encoding.HOI);
			}
		case '~' :
			{
				return e.modifyTone(orig, Encoding.NGA);
			}
		case '\'' :
			{
				return e.modifyTone(orig, Encoding.SAC);
			}
		case '.' :
			{
				return e.modifyTone(orig, Encoding.NANG);
			}
		default :
			{
				return orig;
			}
	}
}
/**
 * fromTCVN1 method comment.
 */
public java.lang.String fromTCVN1(java.lang.String str) {
	String[] table = getFROM_TCVN1();
	StringBuffer sb = new StringBuffer(str.length());
	boolean needEscape = false;
	char c;
	for (int i = 0; i < str.length(); i++){
		c = str.charAt(i);
		if (needEscape && (c == '.' || c == '?')) {
			sb.append('\\');
		}
		sb.append(table[c]);
		needEscape = TCVN1Encoding.getInstance().isVowel(c);
	}
	return sb.toString();
}
	public String getID() { return ID; }
	public static Encoding getInstance() {
	return instance;
	}
public java.lang.String toTCVN1(java.lang.String str) {
	String s = super.toTCVN1(str);
	return s.replace('\\', ' ');
}

protected int getMaxBytes() {
	return 3;
}
}
