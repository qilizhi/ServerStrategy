package com.dz.ip.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name="ip2location_db4")
@Entity
public class Ip2location implements Serializable {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Long ipTo;
  private Long ipFrom;
  private String countryName;
  private String countryCode;
  private String cityName;
  private String regionName;
  private String isp;
 @Id
 @Column(name="ip_to")
 @GeneratedValue
public Long getIpTo() {
	return ipTo;
}
public void setIpTo(Long ipTo) {
	this.ipTo = ipTo;
}
@Column(name="ip_from")
public Long getIpFrom() {
	return ipFrom;
}

public void setIpFrom(Long ipFrom) {
	this.ipFrom = ipFrom;
}
@Column(name="country_name")
public String getCountryName() {
	return countryName;
}
public void setCountryName(String countryName) {
	this.countryName = countryName;
}
@Column(name="country_code")
public String getCountryCode() {
	return countryCode;
}
public void setCountryCode(String countryCode) {
	this.countryCode = countryCode;
}
@Column(name="city_name")
public String getCityName() {
	return cityName;
}
public void setCityName(String cityName) {
	this.cityName = cityName;
}
public String getRegionName() {
	return regionName;
}
public void setRegionName(String regionName) {
	this.regionName = regionName;
}
public String getIsp() {
	return isp;
}
public void setIsp(String isp) {
	this.isp = isp;
}
  
 	
}
