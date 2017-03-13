package com.jun.tools;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**编码与解码操作工具类
 * 
 * @author JuN
 *
 */
public final class CodeUtil {
	
	/**将URL编码
	 * 
	 * @param source
	 * @return
	 */
	public static String encodeURL(String source){
		String target;
		try {
			target = URLEncoder.encode(source,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
		return target;	
	}
	
	/**将URL解码
	 * 
	 */
	
	public static String decodeURL(String source){
		String target;
		try {
			target = URLDecoder.decode(source,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		return target;
	}
	
}
