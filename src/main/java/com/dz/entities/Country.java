package com.dz.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Table(name="country")
@Entity
public class Country implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	//private Long countryId;
	private String name;
	private String code;
	private List<Strategy> strategys=new ArrayList<Strategy>();
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(unique=true)
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
	@OneToMany(mappedBy="country" ,fetch=FetchType.LAZY)
	@JsonBackReference
	public List<Strategy> getStrategys() {
		return strategys;
	}
	public void setStrategys(List<Strategy> strategys) {
		this.strategys = strategys;
	}
	

	

}
