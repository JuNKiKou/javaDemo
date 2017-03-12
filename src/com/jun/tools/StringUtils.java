package com.jun.tools;

public class StringUtils {
	
	public static boolean isEmpty(String s) {
		
		if (s != null) {
			s = s.trim() ;
		}
		
		return StringUtils.isEmpty(s) ;
		
	}
	
	public static boolean isNotEmpty(String s) {
		
		return !isEmpty(s) ;
		
	}
	
}
