package com.dz.model;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import com.dz.service.LocaleMessageSourceService;


/**
 *  请示结果返回模板
 * @author qlz
 *
 */
public class Result implements Serializable {
	/**
	 * 
	 */
	Locale locale = LocaleContextHolder.getLocale();
	//@Resource
	//private MessageSource messageSource;
	//@Autowired
	//private LocaleMessageSourceService localMessageSourceService;
	private static final long serialVersionUID = 1L;
	private String code,msg;
	private Object data;
	public Result(){
		
	};
	/**
	 * 
	 * @param code
	 * @param msg msg-code of ResultCode  language loca
	 */
	public Result(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
		//this.msg=localMessageSourceService.getMessage(msg);
	}
	public Result(String code, Object data) {
		super();
		this.code = code;
		this.data = data;
	}
	public Result(Object data) {
		super();
		this.code=ResultCode.SUCCESS;
		this.data = data;
	}
	/**
	 * 
	 * @param code
	 * @param msg msg-code of ResultCode  language local
	 * @param data
	 */
	public Result(String code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		//this.msg=localMessageSourceService.getMessage(msg);
		this.data = data;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	
	/*public  String getMessage(String resultCode){
		return messageSource.getMessage(resultCode,null, locale);
		
	}*/

}
