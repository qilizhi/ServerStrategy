package com.dz.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Table
@Entity
public class OperationLog implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6201787978870466084L;
	private Long id;
	private Long userId;
	private String username;
	private String path;
	private String method;
	private String className;
	private String params;
	private String result;
	private String content;
	private String operation;
	private Date createTime;
	private Date startTime;
	private Date endTime;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	@Lob
	public String getParams() {
		return params;
	}


	public void setParams(String params) {
		this.params = params;
	}

	@Lob
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		if (createTime == null)
			createTime = new Date();
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Lob
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public OperationLog(Long id, Long userId, String username, String path,
			String method, String params, String result, String content,
			String operation, Date createTime) {
		this.id = id;
		this.userId = userId;
		this.username = username;
		this.path = path;
		this.method = method;
		this.params = params;
		this.result = result;
		this.content = content;
		this.operation = operation;
		this.createTime = createTime;
	}

	public OperationLog() {

	}

}
