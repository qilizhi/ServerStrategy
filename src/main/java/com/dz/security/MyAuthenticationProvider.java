/*package com.dz.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.dz.service.SpringDataUserDetailsService;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private SpringDataUserDetailsService userDetailsService;
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		String username=authentication.getName();
		  String password = (String) authentication.getCredentials();
		  User user=(User) userDetailsService.loadUserByUsername(username);
		  if(user == null){
	            throw new BadCredentialsException("Username not found.");
	        }
		 
		//加密过程在这里体现
		  Md5PasswordEncoder md5Encoder=new Md5PasswordEncoder();
		  password= md5Encoder.encodePassword(password, username);//username 作为salt
	        if (!password.equals(user.getPassword())) {
	            throw new BadCredentialsException("Wrong password.");
	        }
	        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
	        return new UsernamePasswordAuthenticationToken(user, password, authorities);
	}

	public boolean supports(Class<?> authentication) {
		return true;
	}

}
*/