package com.jun.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射工具类
 * */
public class ReflectionUtil {
	
	/**
	 * 创建实例
	 * */
	public static Object newInstance(Class<?> c) {
		Object instance = null ;
		try {
			instance = c.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return instance ;
	}
	
	/**
	 * 调用方法
	 * */
	public static Object invokeMethod(Object object,Method method,Object... args){
		Object result = null;
		method.setAccessible(true);
		try {
			result = method.invoke(object, args);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 * 设置成员变量的值
	 */
	public static void setField(Object object,Field field,Object value) {
		field.setAccessible(true);
		try {
			field.set(object, value);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
