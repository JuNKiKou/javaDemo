package com.jun.helper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.jun.annotation.Action;
import com.jun.bean.Handler;
import com.jun.bean.Request;

/**
 * 控制器助手类
 * @author JuN
 *
 */
public class ControllerHelper {
	
	/**
	 * 用于存放请求和处理器的映射关系
	 */
	
	private static final Map<Request,Handler> actionMap = new HashMap<Request,Handler>();
	
	static {
		
		//获取所有的controller类
		Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
		if (!controllerClassSet.isEmpty()) {
			//遍历controller
			for (Class<?> controller : controllerClassSet) {
				//获取controller中定义的方法
				Method[] methods = controller.getDeclaredMethods();
				if (methods != null) {
					//遍历方法
					for (Method method : methods) {
						//判断方法是否带有Action注解
						if (method.isAnnotationPresent(Action.class)) {
							//从Action注解中获取URL映射规则
							Action action = method.getAnnotation(Action.class);
							String mapping = action.value();
							//验证URL映射规则
							if (mapping.matches("\\w+:/\\w*")) {
								String[] array = mapping.split(":");
								if (array != null && array.length == 2) {
									//获取请求方法和请求路径
									String requestMethod = array[0];
									String requestPath = array[1];
									Request request = new Request(requestMethod, requestPath);
									Handler handler = new Handler(controller, method);
									//初始化action map
									actionMap.put(request, handler);
								}
							}
						}
					}
				}
			}
		}
		
	}
	
	/**
	 * 获取handler
	 */
	
	public static Handler getHandler(String requestMethod,String requestPath){
		Request request = new Request(requestMethod, requestPath);
		return actionMap.get(request);
	}
	
}
