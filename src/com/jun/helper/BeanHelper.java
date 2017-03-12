package com.jun.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.jun.util.ReflectionUtil;

/**
 * bean助手类
 * @author JuN
 *
 */
public class BeanHelper {
	/**
	 * 定义Bean映射(用于存放bean类和bean实例的映射关系)
	 */
	private static final Map<Class<?>, Object> beanMap = new HashMap<Class<?>,Object>();
	
	static {
		Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
		for (Class<?> bean : beanClassSet) {
			Object object = ReflectionUtil.newInstance(bean);
			beanMap.put(bean, object);
		}
	}
	
	/**
	 * 
	 * 获取bean映射
	 */
	public static Map<Class<?>, Object> getBeanMap() {
		return beanMap;
	}
	
	/**
	 * 获取bean实例
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> c) {
		if (!beanMap.containsKey(c)) {
			throw new RuntimeException("can not get bean by class :" +c);
		}
		return (T) beanMap.get(c);
	}
}
