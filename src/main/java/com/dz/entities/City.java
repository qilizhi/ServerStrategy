package com.dz.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Table(name="city")
@Entity
public class City implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	//private Long cityId;
	private String name;
	private String code;
	private Long regionId;
	/*private List<Strategy> strategys=new ArrayList<Strategy>();*/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getRegionId() {
		return regionId;
	}
	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}
	/*@OneToMany(mappedBy="city")
	@JsonBackReference
	public List<Strategy> getStrategys() {
		return strategys;
	}
	public void setStrategys(List<Strategy> strategys) {
		this.strategys = strategys;
	}*/
	
}
