package com.dz.enums;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public enum Status {
	START("启用",1),STOP("停用",0);
	
	private Status(String name, Integer value) {
		this.name = name;
		this.value = value;
	}
	private String name;
	private Integer value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	
	/**
	 * 返回map类型形式
	 * 
	 * @return
	 */

	public static Map<Integer, String> getMap() {
		Map<Integer, String> map = new LinkedHashMap<Integer, String>();
		for( Status status : Status.values() ) {
			map.put( status.getValue(), status.getName() );
		}
		return map;
	}
	public static Integer getValue(String name){
		for( Status status : Status.values() ) {
			if(status.getName().equalsIgnoreCase(name)){
				return status.getValue();
			}
		}
		return null;
	}

	/**
	 * 返回list类型形式
	 * 
	 * @return
	 */
	public static List<Status> getList() {
		return Arrays.asList( Status.values() );
	}
}
