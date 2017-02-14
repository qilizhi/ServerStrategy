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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
/**
 * @category entity
 * @author qlz
 *
 */
@Entity
@Table
public class Server implements Serializable {
	/**
	 * 视频服务器信息
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String domain;//服务器域名ip/url
	private String country;
	private String region;
	private String city;
	private String isp;
	private Integer priority;//优先级
	private Integer maxCapacity;//最大承载量
	private Integer  actualCapacity;//已承载人数
	private Integer status;//状态，1，启用 0停用
	private ServerType type;//类型
	/*private Integer isDefault;*/
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
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getIsp() {
		return isp;
	}
	public void setIsp(String isp) {
		this.isp = isp;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Integer getMaxCapacity() {
		return maxCapacity;
	}
	public void setMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
	@Column(columnDefinition="int default 0")
	public Integer getActualCapacity() {
		//if(this.actualCapacity==null)return 0;
		return actualCapacity;
	}
	public void setActualCapacity(Integer actualCapacity) {
		this.actualCapacity = actualCapacity;
	}



	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@ManyToOne
	public ServerType getType() {
		return type;
	}

	public void setType(ServerType type) {
		this.type = type;
	}

	@ManyToMany(mappedBy="servers" ,fetch=FetchType.LAZY)
	@JsonBackReference
	public List<Strategy> getStrategys() {
		return strategys;
	}

	public void setStrategys(List<Strategy> strategys) {
		this.strategys = strategys;
	}

	/*public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}*/
	
	
	
}
