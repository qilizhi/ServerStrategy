package com.dz.security;

import java.util.Locale;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.dz.enums.Status;
import com.dz.interceptor.ThymeleafLayoutInterceptor;
import com.dz.service.LocaleMessageSourceService;

/**
 * 自定义相关的mvc 配置
 * 
 * @author qlz
 * 
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 增加模板处理器
		registry.addInterceptor(new ThymeleafLayoutInterceptor());
		// 多语言变换处理器
		registry.addInterceptor(localeChangeInterceptor());
	}

	// 常量初始化

	@Bean(name = "EStatus")
	public Map<Integer, String> getStatus() {
		return Status.getMap();
	}

	/*
	 * @Bean(name="EType") public List<ServerType> getType(){ return
	 * serverTypeReposity.findAll(); }
	 */

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
		return slr;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}
	@Bean("permissionEV")
	public MyPermissionEvaluator myPermissionEvaluator(){
		return new MyPermissionEvaluator();
	}
	@Bean("localeMessageSourceService")
	public LocaleMessageSourceService getMessageSource(){
		return new LocaleMessageSourceService();
	}
/*	@Bean(name="springSecurityDialect")
	public SpringSecurityDialect springSecurity(){
		return new SpringSecurityDialect();
		
	}*/
	
/*	@Bean(name="templateEngine")
	public SpringTemplateEngine engine(){
		SpringTemplateEngine e=	new SpringTemplateEngine();
		 SpringTemplateEngine engine = new SpringTemplateEngine();       
         Set<IDialect> dialects = new HashSet<IDialect>();
         dialects.add("springSecurityDialect");
         engine.setAdditionalDialects(dialects);     
         return engine;
		return e;
	}*/
   /* @Bean
    public SpringSecurityDialect springSecurityDialect() {
        SpringSecurityDialect dialect = new SpringSecurityDialect();
        return dialect;
    }
	@Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();

        Set<IDialect> dialects = new HashSet<IDialect>();
        dialects.add(new SpringSecurityDialect());
        templateEngine.setAdditionalDialects(dialects);  
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }*/
	

/*	    @Bean
	    public SpringTemplateEngine templateEngine() {
	        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	        Set<IDialect> dialects = new HashSet<IDialect>();
	        dialects.add(springSecurityDialect());
	        templateEngine.setAdditionalDialects(dialects);  
	        return templateEngine;
	    }
*/
/*	    @Bean
	    public ThymeleafViewResolver thymeleafViewResolver() {
	        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
	        resolver.setTemplateEngine(templateEngine());
	        return resolver;
	    }*/

	   /*  @Bean
	     public SpringSecurityDialect springSecurityDialect() {
	         SpringSecurityDialect dialect = new SpringSecurityDialect();
	         return dialect;
	     }*/
	   /*  @Bean
	     public ConditionalCommentsDialect conditionalCommentDialect() {
	         return new ConditionalCommentsDialect();
	     }*/
		

}
