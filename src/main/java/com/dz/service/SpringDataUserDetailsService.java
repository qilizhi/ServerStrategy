package com.dz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.dz.entities.Authority;
import com.dz.entities.Role;
import com.dz.entities.User;
import com.dz.model.UserInfo;
import com.dz.repository.UserReposity;

@Component
public class SpringDataUserDetailsService  implements UserDetailsService {
	@Autowired
	private UserReposity userResposity;
	
	@Override
	public UserInfo loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user=userResposity.findOneByUsername(username);
		if(user==null){
			return null;
		}
		List<GrantedAuthority> grantedAuthorities=new ArrayList<GrantedAuthority>();
		for(Role r:user.getRoles()){
			for(Authority a:r.getAuthorities()){
				GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(a.getUri());
				grantedAuthorities.add(grantedAuthority);
			}
		}
		//return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),grantedAuthorities);
		return new UserInfo(user.getId(),user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getPhone(),grantedAuthorities);
	}
	/*public boolean authorized(User user,String authened,String permission){
		//判断是否有权限
		for(Role r:user.getRoles()){
			for(Authority a:r.getAuthorities()){
				if(a.getUri().contains(authened)){
					return true;
				};
			}
		}
		return false;
		
	}*/

}
