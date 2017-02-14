package com.dz.security;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.dz.entities.Role;
import com.dz.entities.User;
import com.dz.repository.UserReposity;

@Component
public class MyPermissionEvaluator implements PermissionEvaluator {
	@Autowired
	private UserReposity userReposity;
	//@Autowired
	//private RoleRepository roleRepository;

	@Override
	public boolean hasPermission(Authentication authentication,
			Object targetDomainObject, Object permission) {
		// TODO Auto-generated method stub
		//authentication.get
		String username = authentication.getName();
		if(username==null||"".equals(username)){
			return false;
		}
		if("super".equalsIgnoreCase(username)){
			return true;
		}
		//User user= userReposity.findOneByUsername(username);
		/*if(user!=null){
	
		for(Role r :user.getRoles()){
			if("admin".equalsIgnoreCase(r.getName())||"管理员".equalsIgnoreCase(r.getName())){
				return true;
			}
		}
		}*/
		//判权限是否正确
		for(GrantedAuthority a:authentication.getAuthorities()){
			if(a.getAuthority().indexOf(targetDomainObject.toString())>-1){
			return true;	
			}
		}
		
		return false;
	}

	@Override
	public boolean hasPermission(Authentication authentication,
			Serializable targetId, String targetType, Object permission) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * 判断是否有权限
	 * @param uri
	 * @return
	 */
	public boolean hasAuth(String uri){
		Authentication auth=SecurityContextHolder.getContext() .getAuthentication();
		return hasPermission(auth,uri,"");
	}

}
