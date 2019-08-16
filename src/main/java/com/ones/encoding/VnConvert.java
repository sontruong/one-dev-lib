package com.ones.encoding;

import java.util.Enumeration;
import java.util.Hashtable;

public class VnConvert {
	@SuppressWarnings("rawtypes")
	protected static Hashtable h = new Hashtable(11);
	public static Encoding[] ENCODINGS = { TCVN1Encoding.getInstance(), TCVN3Encoding.getInstance(),
			VIQREncoding.getInstance(), VISCIIEncoding.getInstance(), VNIEncoding.getInstance(),
			VPSEncoding.getInstance(), new UnicodeEncoding() };

	static {
		initialize();
	}

	public static String convert(String str, Encoding fromEnc, Encoding toEnc) {
		return toEnc.fromTCVN1(fromEnc.toTCVN1(str));
	}

	private static void initialize() {
		for (int i = 0; i < ENCODINGS.length; i++) {
			Encoding e = ENCODINGS[i];
			h.put(e.getID().toLowerCase(), e);
		}
		h.put("uni", new UnicodeEncoding());
	}

//	public static void main(String[] args) throws IOException {
//		if (args.length != 4) {
//			usage();
//			System.exit(1);
//		}
//		Encoding e1 = (Encoding) h.get(args[0].toLowerCase());
//		Encoding e2 = (Encoding) h.get(args[2].toLowerCase());
//		if (e1 == e2) {
//			System.out.println("Same encoding. Nothing to do!");
//			System.exit(0);
//		}
//		String inEnc = System.getProperty("inEnc", "latin1");
//		String outEnc = System.getProperty("outEnc", "utf-8");
//		String line;
//		BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(args[1]), inEnc));
//		PrintWriter output = new PrintWriter(new OutputStreamWriter(new FileOutputStream(args[3]), outEnc));
//		try {
//			while ((line = input.readLine()) != null) {
//				try {
//					output.println(convert(line, e1, e2));
//				} catch (Exception e) {
//				}
//			}
//		} finally {
//			input.close();
//			output.close();
//		}
//	}

	@SuppressWarnings("rawtypes")
	private static void usage() {
		System.out.println("Usage: VnConvert -DinEnc=latin1 -DoutEnc=utf-8 in-encoding in out-encoding out");
		System.out.println("E.g., VnConvert tcvn3 test.vn3 vni test.vni");
		System.out.println("in-encoding and out-encoding can be (case-insensitive):");
		for (Enumeration e = h.keys(); e.hasMoreElements();) {
			System.out.print(e.nextElement().toString() + " ");
		}
	}
}
