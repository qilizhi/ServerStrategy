package com.dz.model;

import java.io.Serializable;

public class IpApiModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IpInformation data;
	private Integer code;

	public IpInformation getData() {
		return data;
	}
	public void setData(IpInformation data) {
		this.data = data;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	 

}
