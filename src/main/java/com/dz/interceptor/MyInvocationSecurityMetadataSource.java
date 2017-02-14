/*package com.dz.interceptor;

import groovy.util.logging.Log4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import com.dz.entities.Authority;
import com.dz.repository.AuthorityRepository;


@Service
@Log4j
public class MyInvocationSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {
	Logger log=LoggerFactory.getLogger(MyInvocationSecurityMetadataSource.class);
	@Autowired
	private AuthorityRepository authorityRepository;
	private HashMap<String, Collection<ConfigAttribute>> map =null;
	
	*//**
	 * 加载资源，初始化资源变量
	 *//*
	public void loadResourceDefine(){
	 map = new HashMap<String,Collection<ConfigAttribute>>();
	 Collection<ConfigAttribute> array=null;
	 ConfigAttribute cfg;
	 List<Authority> authoritys = authorityRepository.findAll();
	for(Authority authority : authoritys) {
	  array = new ArrayList<ConfigAttribute>();
	 cfg = new SecurityConfig(authority.getName());
	 array.add(cfg);
	 map.put(authority.getUri(), array);
	 }
	 log.info("security info load success!!");
	 } 
	
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		if(map ==null) loadResourceDefine();
		 HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
		 AntPathRequestMatcher matcher;
		 String resUrl;
		for(Iterator<String> iter = map.keySet().iterator(); iter.hasNext(); ) {
		 resUrl = iter.next();
		 matcher = new AntPathRequestMatcher(resUrl);
		if(matcher.matches(request)) {
		return map.get(resUrl);
		 }
		 }
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		//return false;
		return true;
	}

}
*/