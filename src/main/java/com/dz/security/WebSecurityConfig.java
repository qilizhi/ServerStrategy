package com.dz.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import com.dz.service.SpringDataUserDetailsService;
//import com.dz.interceptor.MyFilterSecurityInterceptor;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled =true)
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private SpringDataUserDetailsService userDetailsService;
	//@Autowired
	//private MyAuthenticationProvider authenticationProvider;
	//@Autowired
	//private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
				.antMatchers("/resources/**","/resources/templates/**","/templates/**","/error","/css/**","/static/**","/images/**","layout/**","views/**","/assets/css/**","/assets/js/**").permitAll()
				.antMatchers("/ip/syn","/api/**","/login").permitAll()
				.anyRequest().authenticated()
				.and().formLogin()
				.loginPage("/login").permitAll()
				.defaultSuccessUrl("/index")
				.failureUrl("/login/fail").permitAll()
				.and().logout().invalidateHttpSession(true)
				.logoutUrl("/logout")
				.logoutSuccessUrl("/").permitAll()
				.and().rememberMe()
                .and().exceptionHandling().accessDeniedPage("/accessDenied");
		/*http.authorizeRequests()
		.antMatchers("/resources/**", "/error", "/hello").permitAll()
		.anyRequest().authenticated().and().formLogin()
		.loginPage("/login").permitAll().failureUrl("/loginfail")
		.permitAll().and().logout().logoutUrl("loginout");*/
		// http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
		// http.addFilterAfter(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
	}
	 @Override
	    public void configure(WebSecurity web) throws Exception {
	        //web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/**/favicon.ico");
	        web.ignoring()
	     //   .antMatchers("/resources/**","/error","/css/**","/static/**","/images/**","layout/**","views/**","assets/**/**");
	        .antMatchers("/resources/**","/resources/templates/**","/templates/**","/error","/css/**","/static/**","/images/**","layout/**","views/**","/assets/css/**","/assets/js/**","/logout");
	 }

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("super").password("dzadmin")
				.roles("admin");
		//auth.eraseCredentials(false);
		//auth.authenticationProvider(authenticationProvider);
		 auth.userDetailsService(userDetailsService).passwordEncoder(new StandardPasswordEncoder());
		// auth.jdbcAuthentication().dataSource(dataSource).withDefaultSchema();

	}
 
	/*@Bean
	public DaoAuthenticationProvider daoAuthenticationP(){
		DaoAuthenticationProvider daoAuthenticationProvider =new DaoAuthenticationProvider();
		ReflectionSaltSource saltSource= new ReflectionSaltSource();
		saltSource.setUserPropertyToUse("username");
		daoAuthenticationProvider.setSaltSource(saltSource);
		return daoAuthenticationProvider ;
	}
	@Bean
	public SpringDataUserDetailsService springDataUserDetailsService() {
		return new SpringDataUserDetailsService();
	}*/

}