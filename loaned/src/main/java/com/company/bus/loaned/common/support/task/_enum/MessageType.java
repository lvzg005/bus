package com.company.bus.loaned.common.support.task._enum;

/**
 * @Description 消息资源类型 
 * @author 
 * @date 
 * @version 1.0
 */
public enum MessageType {

	/**
	 * 异常资源类型：errors_message.properties
	 */
	ERRORS_TYPE ("errors"),
	
	/**
	 * 字典资源类型
	 */
	DICTS_TYPE ("dicts");
	
	MessageType(String code){
		this.code = code;
	}
	
	private final String code;

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
}
