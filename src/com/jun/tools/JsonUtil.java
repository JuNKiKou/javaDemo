package com.jun.tools;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**JSON工具类
 * 
 * @author JuN
 *
 */
public final class JsonUtil {
	
	private static final ObjectMapper mapper = new ObjectMapper();
	
	/**将POJO转化为json
	 * 
	 */
	
	public static <T> String toJson(T object){
		String json;
		try {
			json = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return json;
	}
	
	/**将json转化为POJO
	 * 
	 */
	
	public static <T> T fromJson(String json,Class<T> type){
		T pojo = null;
		try {
			pojo = mapper.readValue(json, type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return pojo;
	}
	
}
