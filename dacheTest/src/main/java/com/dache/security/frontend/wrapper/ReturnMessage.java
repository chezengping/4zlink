package com.dache.security.frontend.wrapper;

import java.io.Serializable;


/**
 * 
 *  Class Name: ReturnMessage.java
 *  Function: 接口返回信息
 *  
 *  Modifications:   
 *  
 *  @author gxl  DateTime 2015-3-13 下午2:06:57    
 *  @version 1.0
 */

public class ReturnMessage implements Serializable {
	
	public static final String SUCCESS = "0"; //成功
	public static final String FAILED = "1"; //失败
	public static final String OTHER = "2"; //其他
	
	

	private static final long serialVersionUID = 1L;
	
	private String state;	//状态  0、成功 1、失败 2、其他
	
	private String msg;	//消息
	
	private Object data; // 内容

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}


	
	
	

}
