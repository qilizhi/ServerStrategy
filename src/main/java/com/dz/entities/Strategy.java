package com.dz.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Strategy implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;// 名字
	private String description;// 策略说明；
	private Country country;// 国家
	private Boolean isNotCountry;// 是否非国家
	private City city;
	private Region region;
	private List<Isp> isps=new ArrayList<Isp>();//服务提供商
	private Integer status;// 状态
	private Integer isDefault;// 是否默认
	private StrategyType type;// 类型
	private List<Server> servers = new ArrayList<Server>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	@ManyToOne
	public StrategyType getType() {
		return type;
	}

	public void setType(StrategyType type) {
		this.type = type;
	}

	public Boolean getIsNotCountry() {
		return isNotCountry;
	}

	public void setIsNotCountry(Boolean isNotCountry) {
		this.isNotCountry = isNotCountry;
	}
	@ManyToOne
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "strategy_server",joinColumns = { @JoinColumn(name = "strategy_id", referencedColumnName = "id"), }, inverseJoinColumns = { @JoinColumn(name = "server_id", referencedColumnName = "id") })
	public List<Server> getServers() {
		return servers;
	}

	public void setServers(List<Server> servers) {
		this.servers = servers;
	}
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "strategy_isp", joinColumns = { @JoinColumn(name = "strategy_id", referencedColumnName = "id"), }, inverseJoinColumns = { @JoinColumn(name = "isp_id", referencedColumnName = "id") })
	public List<Isp> getIsps() {
		return isps;
	}

	public void setIsps(List<Isp> isps) {
		this.isps = isps;
	}

	@ManyToOne(optional=true)
	public Region getRegion() {
		return region;
	}


	public void setRegion(Region region) {
		this.region = region;
	}

	@ManyToOne(optional=true)
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

}
