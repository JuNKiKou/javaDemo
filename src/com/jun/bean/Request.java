package com.jun.bean;

import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * 封装请求信息
 * @author JuN
 *
 */
public class Request {
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return HashCodeBuilder.reflectionHashCode(this,false);
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public String getRequestPath() {
		return requestPath;
	}

	/**
	 * 请求方法
	 */
	private String requestMethod ;
	
	/**
	 * 请求路径
	 */
	private String requestPath ;

	public Request(String requestMethod, String requestPath) {
		this.requestMethod = requestMethod;
		this.requestPath = requestPath;
	}
	
	
	
	
}
