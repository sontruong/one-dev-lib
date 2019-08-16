package com.ones.encoding;

import java.util.BitSet;

public class SingleByteEncoding extends AbstractEncoding
{
	private char[] DD = new char[2];
	private char[] HND = new char[144];
	private char[] circumflex = new char[6];
	private char[] breve_horn = new char[6];


	private int[] DNH = new int[256];
	private char[] UPPER = null;
	private char[] LOWER = null;

	private BitSet LETTERS = new BitSet(256);
	private BitSet DIACRITICS = new BitSet(256);

	private char[] TO_TCVN1 = null;
	private char[] FROM_TCVN1 = null;
	protected SingleByteEncoding(char[] DD, char[] HND)
	{
	super();
	this.DD = DD;
	this.HND = HND;
	initialize();
	}
   public char addBreveHorn(char c)
   {
	  switch(c)
	  {
		 case 'A':
			return breve_horn[0];
		 case 'O':
			return breve_horn[1];
		 case 'U':
			return breve_horn[2];
		 case 'a':
			return breve_horn[3];
		 case 'o':
			return breve_horn[4];
		 case 'u':
			return breve_horn[5];
		 default:
			{
			   return c;
			}
	  }
   }
   public char addCircumflex(char c)
   {
	  switch(c)
	  {
		 case 'A':
			return circumflex[0];
		 case 'E':
			return circumflex[1];
		 case 'O':
			return circumflex[2];
		 case 'a':
			return circumflex[3];
		 case 'e':
			return circumflex[4];
		 case 'o':
			return circumflex[5];
		 default:
			{
			   return c;
			}
	  }
   }
   public char addStroke(char c)
   {
	  switch(c)
	  {
		 case 'D':
			return DD[0];
		 case 'd':
			return DD[1];
		 default:
			{
			   return c;
			}
	  }
   }
/**
 * fromTCVN1 method comment.
 */
public java.lang.String fromTCVN1(java.lang.String str) {
	char[] data = new char[str.length()];
	char[] table = getFROM_TCVN1();
	for (int i = 0; i < data.length; i++){
		data[i] = table[str.charAt(i) & 0xff];
	}
	return new String(data);
}
/**
 * Insert the method's description here.
 * Creation date: (3/16/00 2:30:17 PM)
 * @return char[]
 */
protected char[] getFROM_TCVN1() {
	if (FROM_TCVN1 == null) {
		initFROM_TCVN1();
	}
	return FROM_TCVN1;
}
/**
 * Insert the method's description here.
 * Creation date: (3/16/00 2:17:32 PM)
 * @return char[]
 */
protected char[] getLOWER() {
	if (LOWER == null) {
		initLOWER();
	}
	return LOWER;
}
/**
 * Insert the method's description here.
 * Creation date: (3/16/00 2:17:32 PM)
 * @return char[]
 */
protected char[] getTO_TCVN1() {
	if (TO_TCVN1 == null) {
		initTO_TCVN1();
	}
	return TO_TCVN1;
}
/**
 * Insert the method's description here.
 * Creation date: (3/16/00 2:17:32 PM)
 * @return char[]
 */
protected char[] getUPPER() {
	if (UPPER == null) {
		initUPPER();
	}
	return UPPER;
}
   public boolean hasDiacritic(char c)
   {
   	return DIACRITICS.get((int)c);
   }
/**
 * Insert the method's description here.
 * Creation date: (3/16/00 2:17:32 PM)
 */
private void initFROM_TCVN1() {
	int size = 256;
	FROM_TCVN1 = new char[size];
	for (int i = 0; i < size; i++){
		FROM_TCVN1[i] = (char)i;
	}
	for (int i = 0; i < DD.length; i++){
		FROM_TCVN1[TCVN1_DD[i]] = DD[i];
	}
	for (int i = 0; i < HND.length; i++){
		FROM_TCVN1[TCVN1_HND[i]] = HND[i];
	}
}
   protected void initialize()
   {
	   initializeChars();
	   int i;
	   for(i = 0;i < DNH.length;i++) DNH[i] = -1;
	   for(i = 0;i < HND.length;i++) DNH[(int)HND[i]] = i;
	   initializeLETTERS();
	   initializeDIACRITICS();
   }
	protected void initializeChars() {
	circumflex[0] = (char)HND[12];
	circumflex[1] = (char)HND[24];
	circumflex[2] = (char)HND[42];
	circumflex[3] = (char)HND[84];
	circumflex[4] = (char)HND[96];
	circumflex[5] = (char)HND[114];
	breve_horn[0] = (char)HND[6];
	breve_horn[1] = (char)HND[48];
	breve_horn[2] = (char)HND[60];
	breve_horn[3] = (char)HND[78];
	breve_horn[4] = (char)HND[120];
	breve_horn[5] = (char)HND[132];
	}
protected void initializeDIACRITICS() {
	int i = 0;
	for (i = 0; i < HND.length; i++) {
		char c = HND[i];
		if (!(c >= 'A' && c <= 'Z') && !(c >= 'a' && c <= 'z')) DIACRITICS.set(c);
	}
	for (i = 0; i < DD.length; i++)
		DIACRITICS.set(DD[i]);
}
   protected void initializeLETTERS()
   {
   	int i;
   	for (i=(int)'a'; i<=(int)'z'; i++) LETTERS.set(i);
   	for (i=(int)'A'; i<=(int)'Z'; i++) LETTERS.set(i);
   	for (i=0; i<HND.length; i++) LETTERS.set(HND[i]);
   	for (i=0; i<DD.length; i++) LETTERS.set(DD[i]);
   }
/**
 * Insert the method's description here.
 * Creation date: (3/16/00 2:17:32 PM)
 */
private void initLOWER() {
	int size = 256;
	LOWER = new char[size];
	
	for (int i = 0; i < size; i++){
		LOWER[i] = Character.toLowerCase((char)i);
	}
	
	LOWER[DD[0]] = DD[1];
	LOWER[DD[1]] = DD[1];
	
	for (int i = 0; i < HND.length/2; i++){
		LOWER[HND[i]] = HND[i+(HND.length/2)];
	}
	for (int i = HND.length/2; i < HND.length; i++){
		LOWER[HND[i]] = HND[i];
	}
}
/**
 * Insert the method's description here.
 * Creation date: (3/16/00 2:17:32 PM)
 */
private void initTO_TCVN1() {
	int size = 256;
	TO_TCVN1 = new char[size];
	for (int i = 0; i < size; i++){
		TO_TCVN1[i] = (char)i;
	}
	for (int i = 0; i < DD.length; i++){
		TO_TCVN1[DD[i]] = TCVN1_DD[i];
	}
	for (int i = 0; i < HND.length; i++){
		TO_TCVN1[HND[i]] = TCVN1_HND[i];
	}
}
/**
 * Insert the method's description here.
 * Creation date: (3/16/00 2:17:32 PM)
 */
private void initUPPER() {
	int size = 256;
	UPPER = new char[size];
	
	for (int i = 0; i < size; i++){
		UPPER[i] = Character.toUpperCase((char)i);
	}
	
	UPPER[DD[0]] = DD[0];
	UPPER[DD[1]] = DD[0];
	
	for (int i = 0; i < HND.length/2; i++){
		UPPER[HND[i]] = HND[i];
	}
	for (int i = HND.length/2; i < HND.length; i++){
		UPPER[HND[i]] = HND[i-(HND.length/2)];
	}
}
   public boolean isLetter(char c)
   {
   	return LETTERS.get((int)c);
   }
   public boolean isVowel(char c)
   {
   	return DNH[c&0xff] >= 0;
   }
   /**
	* @param original The original string
	* @param toneMark The tone mark encoding, a number between 0 and 5.
	* It is the index of the tone mark's name in toneNames.
	*/
   public char modifyTone(char original, int toneMark)
   {
	  int i = DNH[(int)original];
	  if(i < 0)
	  {
		 return original;
	  }
	  return HND[i - (i % 6) + toneMark];
   }
/**
 * toLowerCase method comment.
 */
public char toLowerCase(char c) {
	return getLOWER()[c&0xff];
}
/**
 * toTCVN1 method comment.
 */
public java.lang.String toTCVN1(java.lang.String str) {
	char[] data = new char[str.length()];
	char[] table = getTO_TCVN1();
	for (int i = 0; i < data.length; i++){
		data[i] = table[str.charAt(i)&0xff];
	}
	return new String(data);
}
/**
 * toUpperCase method comment.
 */
public char toUpperCase(char c) {
	return getUPPER()[c&0xff];
}
}
