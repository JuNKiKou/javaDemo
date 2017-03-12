package com.jun.helper;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.jun.annotation.Controller;
import com.jun.annotation.Service;
import com.jun.tools.ClassUtils;

public class ClassHelper {
	
	private static Set<Class<?>> classSet ;
	
	static {
		String basePackage = "com.jun.application" ;
		try {
			classSet = ClassUtils.getClassSet(basePackage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取应用包名下的所有类/
	 * @return
	 */
	public static Set<Class<?>> getClassSet(){
		return classSet ;
	}
	
	/** 
	 * 获取应用包名下所有的service类
	 */
	public static Set<Class<?>> getServiceClassSet() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		for (Class<?> c : classes) {
			if (c.isAnnotationPresent(Service.class)) {
				classes.add(c);
			}
		}
		return classes;
	}
	/**
	 * 
	 * 获取应用包名下所有的controller类
	 * */
	public static Set<Class<?>> getControllerClassSet() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		for (Class<?> c : classes) {
			if (c.isAnnotationPresent(Controller.class)) {
				classes.add(c);
			}
		}
		return classes;
	}
	
	/**
	 * 
	 * 获取应用包名下所有的bean类(包括controller和service等)
	 * */
	public static Set<Class<?>> getBeanClassSet() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.addAll(getServiceClassSet());
		classes.addAll(getControllerClassSet());
		return classes;
	}
	
}
