package com.jun.loader;

import com.jun.helper.BeanHelper;
import com.jun.helper.ClassHelper;
import com.jun.helper.ControllerHelper;
import com.jun.helper.IocHelper;
import com.jun.tools.ClassUtils;

/**加载相应的helper
 * 
 * @author JuN
 *
 */
public final class HelperLoader {
	
	public static void init(){
		
		Class<?>[] classes = {
			ClassHelper.class,
			BeanHelper.class,
			ControllerHelper.class,
			IocHelper.class
		};
		
		for (Class<?> c : classes) {
			ClassUtils.loadClass(c.getName(),false);
		}
	}
	
}
