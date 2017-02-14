package com.dz.security;

import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.QueryLookupStrategy.Key;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.dz.base.repository.base.BaseRepositoryFactoryBean;
import com.dz.base.repository.base.BaseRepositoryImpl;

@Configuration
@EntityScan("com.dz.entities")
//@PropertySource({ "classpath:primary.datasource.properties" })
@ConfigurationProperties(prefix = "spring.jpa.primary")
@EnableJpaRepositories(transactionManagerRef="primaryTransactionManager",entityManagerFactoryRef="primaryEntityManagerFactory",basePackages = "com.dz.repository", queryLookupStrategy = Key.CREATE_IF_NOT_FOUND, repositoryBaseClass = BaseRepositoryImpl.class, repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class, repositoryImplementationPostfix = "Impl")

public class PrimaryJpaRepConfig {

	
	@Bean("primaryDataSource")
	//@Primary
	@ConfigurationProperties(prefix = "spring.datasource.primary")
	public DataSource primaryDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean("primaryEntityManagerFactory")
	@Primary
	@ConfigurationProperties(prefix = "spring.jpa.primary")
	public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(
			EntityManagerFactoryBuilder builder, JpaProperties jpaProperties,@Qualifier("primaryDataSource") DataSource dataSource) {
		Map<String,String> mappro=jpaProperties.getHibernateProperties(dataSource);
		return builder
				.dataSource(dataSource)
				.packages("com.dz.entities")
				.properties(mappro)
				.persistenceUnit("video").build();
	}

	@Bean("primaryTransactionManager")
	@Primary
	public PlatformTransactionManager primaryTransactionManager(
			@Qualifier("primaryEntityManagerFactory")EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}


}
