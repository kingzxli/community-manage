package com.community.exception;

/**
 * 自定义异常
 * @author king
 *
 */
public class CustomException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private int code = 400;

	private String msg;

	public CustomException() {

	}

	public CustomException(String msg) {
		this.msg = msg;
	}

	public CustomException(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
