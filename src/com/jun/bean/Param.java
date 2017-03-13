package com.jun.bean;

import java.util.Map;

/**请求参数对象
 * 
 * @author JuN
 *
 */
public class Param {
	
	private Map<String,Object> paramMap ;

	public Param(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
	
	/**根据参数名获取Long型参数值
	 * 
	 */
	public long getLong(String name){
		return (long) paramMap.get(name);
	}
	
	/**获取所有字段的信息
	 * 
	 */
	public Map<String, Object> getMap(){
		return paramMap;
	}

	
	
	
	
}
