package com.dz.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
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
public class ServerType {
	private Long id;
	private String name;
	private List<Server> servers=new ArrayList<Server>();
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
	@OneToMany(mappedBy="type")
	@JsonBackReference
	public List<Server> getServers() {
		return servers;
	}
	public void setServers(List<Server> servers) {
		this.servers = servers;
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
