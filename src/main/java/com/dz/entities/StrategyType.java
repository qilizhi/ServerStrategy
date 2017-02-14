package com.dz.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * 服务器类型
 * @author qlz
 *
 */
@Entity
@Table
public class StrategyType {
	private Long id;
	private String name;
	private List<Strategy> strategys=new ArrayList<Strategy>();
	private Long total;
	private Long start;
	private Long stop;
	
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
	@OneToMany(mappedBy="type",fetch=FetchType.LAZY)
	@JsonBackReference
	public List<Strategy> getStrategys() {
		return strategys;
	}
	public void setStrategys(List<Strategy> strategys) {
		this.strategys = strategys;
	}
	@Transient
	public Long getTotal() {
		return total;
	}
	
	public void setTotal(Long total) {
		this.total = total;
	}
	@Transient
	public Long getStart() {
		return start;
	}
	public void setStart(Long start) {
		this.start = start;
	}
	@Transient
	public Long getStop() {
		return stop;
	}
	public void setStop(Long stop) {
		this.stop = stop;
	}
	

}
