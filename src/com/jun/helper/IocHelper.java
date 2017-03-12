package com.jun.helper;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;

import com.jun.annotation.Inject;
import com.jun.util.ReflectionUtil;

/**
 * 依赖注入助手类
 * @author JuN
 *
 */
public class IocHelper {
	
	static {
		//获取所有的bean类与bean实例之间的映射关系
		Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
		if (!beanMap.isEmpty()) {
			//遍历map
			for (Map.Entry<Class<?>, Object>beanEntry: beanMap.entrySet()) {
				Class<?> beanClass = beanEntry.getKey();
				Object beanInstance = beanEntry.getValue();
				//获取bean类中定义的所有成员变量
				Field[] fields = beanClass.getDeclaredFields();
				if (!(fields == null)) {
					//遍历bean field
					for (Field field : fields) {
						//判断是否带有@Inject注解
						if (field.isAnnotationPresent(Inject.class)) {
							Class<?> fieldClass = field.getType();
							Object fieldInstance = beanMap.get(fieldClass);
							if (fieldInstance != null) {
								//通过反射初始化filed的值
								ReflectionUtil.setField(beanInstance, field, fieldInstance);
							}
						}
					}
				}
			}
		}
	}
	
}
