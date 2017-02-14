package com.dz.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Table(name="isp")
@Entity
public class Isp {

	private Long id;
	private String name;
	private String code;
	private Long countryId;
	private List<Strategy> strategys=new ArrayList<Strategy>();
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
	@Column(nullable=false)
	public Long getCountryId() {
		return countryId;
	}
	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}
	@ManyToMany(mappedBy="isps")
@JsonBackReference
	public List<Strategy> getStrategys() {
		return strategys;
	}
	public void setStrategys(List<Strategy> strategys) {
		this.strategys = strategys;
	}
	

	
}
