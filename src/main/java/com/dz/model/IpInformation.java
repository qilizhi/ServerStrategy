package com.dz.model;

import java.io.Serializable;

public class IpInformation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String region_name;
	private String city_name;
	private String isp;
	private String country_name;
	private String country_code;
	private String ip;
	public String getRegion_name() {
		return region_name;
	}
	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getIsp() {
		return isp;
	}
	public void setIsp(String isp) {
		this.isp = isp;
	}
	public String getCountry_name() {
		return country_name;
	}
	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getCountry_code() {
		return country_code;
	}
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}
	@Override
	public String toString() {
		return "IpInformation [region_name=" + region_name + ", city_name="
				+ city_name + ", isp=" + isp + ", country_name=" + country_name
				+ ", country_code=" + country_code + ", ip=" + ip + "]";
	}
	

}
