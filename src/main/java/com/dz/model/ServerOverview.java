package com.dz.model;

import java.io.Serializable;

public class ServerOverview  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String typeName;
	private Long totoal;
	private Long start;
	private Long stop;
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Long getTotoal() {
		return totoal;
	}
	public void setTotoal(Long totoal) {
		this.totoal = totoal;
	}
	public Long getStart() {
		return start;
	}
	public void setStart(Long start) {
		this.start = start;
	}
	public Long getStop() {
		return stop;
	}
	public void setStop(Long stop) {
		this.stop = stop;
	}

}
