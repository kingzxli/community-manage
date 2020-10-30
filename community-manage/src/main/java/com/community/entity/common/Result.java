package com.community.entity.common;

import java.io.Serializable;

public class Result<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	static final String SUCCESS_MESSAGE = "操作成功";
	static final String FAILURE_MESSAGE = "操作失败";
	static final Integer OK = 0;
	static final Integer FAILTURE = 400;
	/**
	 * 状态码
	 */
	private Integer code = OK;
	
	private T data;
	
	private Integer total = 0;

	private String message;
	
	public static final Result<?> SUCCESS = success();
	
	public static final Result<?> FAILURE = failure();
	
	private static Result<?> success() {
		return new Result<>(OK, SUCCESS_MESSAGE);
	}
	
	private static Result<?> failure() {
		return new Result<>(FAILTURE, FAILURE_MESSAGE);
	}
	
	public static Result<?> create(String message) {
		return new Result<>(message);
	}
	
	public static Result<?> create(Integer code, String message) {
		return new Result<>(code, message);
	}
	
	public Result() {
		this.code = OK;
		this.message = SUCCESS_MESSAGE;
	}

	public Result(T t) {
		this.code = OK;
		this.message = SUCCESS_MESSAGE;
		this.data = t;
	}

	public Result(Integer code, T data) {
		this.code = code;
		this.data = data;
	}

	public Result(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public Result(Integer code, T data, Integer total) {
		this.code = code;
		this.data = data;
	}

	public Result(Integer code, String message, Integer total) {
		this.code = code;
		this.message = message;
	}
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Result<T> code(Integer code){
		this.code = code;
		return this;
	}
	
	public Result<T> msg(String message){
		this.message = message;
		return this;
	}
	
	public Result<T> data(T data){
		this.data = data;
		return this;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	
	public Result<T> total(Integer total) {
		this.total = total;
		return this;
	}
	
	public Result<T> total(Long total) {
		this.total = total == null ? 0 : total.intValue();
		return this;
	}

	public String getMsg() {
		return message;
	}

	public void setMsg(String msg) {
		this.message = msg;
	}
	
}
