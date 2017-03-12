package com.jun.generictype;

import java.util.ArrayList;
import java.util.Date;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
//      no generic type (raw type)		
//		ArrayList dates = new ArrayList();
//		dates.add(new Date());
//		Date date = (Date) dates.get(0);
		
//      generic type		
//		ArrayList<Date> dates = new ArrayList<Date>();
//		dates.add(new Date());
//		Date date = dates.get(0);		
//		System.out.println(date.toString());
		
		System.out.println(System.getProperty("java.class.path"));
		
		Class typeLoader = null;
		try {
			typeLoader = Class.forName("com.jun.bean.Beans");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(typeLoader.getClassLoader());
		
	}
	
//	private static void demo(int... args){
//		System.out.println("A------");
//	}
	
	

}
