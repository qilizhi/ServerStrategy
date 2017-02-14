package com.dz.enums;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum Sex {
	BOY(1,"男"),
	GIRL(2,"女"),
	UNKNOWN(0,"未知");
	
	private Integer value;
	private String name;
	
	

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	private Sex(Integer value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}
	
	/**
	 * 返回map类型形式
	 * 
	 * @return
	 */
	public static Map<Integer, String> getMap() {
		Map<Integer, String> map = new LinkedHashMap<Integer, String>();
		for( Sex status : Sex.values() ) {
			map.put( status.getValue(), status.getName() );
		}
		return map;
	}

	/**
	 * 返回list类型形式
	 * 
	 * @return
	 */
	public static List<Sex> getList() {
		return Arrays.asList(Sex.values() );
	}

}
