package com.jun.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**流操作工具类
 * 
 * @author JuN
 *
 */
public final class StreamUtil {
	
	/**从输入流中获取字符串
	 * 
	 */
	
	public static String getString(InputStream inputStream){
		
		
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		String line;
		try {
			while((line = reader.readLine()) != null){
				builder.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
		return builder.toString();
		
		
		
	}
	
}
