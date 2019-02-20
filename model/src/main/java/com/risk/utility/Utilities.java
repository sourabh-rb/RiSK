package com.risk.utility;

public class Utilities {
	
	/**
	 * This method gets the integer value for the string that is passed to it.
	 * 
	 * @param s The string that is to be converted to integer.
	 * @return The integer value.
	 */
	public static Integer getIntegerValue(String s) {
		StringBuilder sb = new StringBuilder();
	    for (char c : s.toCharArray())
	    sb.append((int)c);

	    Integer mInt = new Integer(sb.toString());
	    return mInt;
	}

}
