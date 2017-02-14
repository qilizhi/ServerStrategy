package com.dz.security;

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
@EntityScan("com.dz.ip.entities")
//@PropertySource({ "classpath:secondary.datasource.properties" })
@ConfigurationProperties(prefix = "spring.jpa.secondary")
@EnableJpaRepositories(transactionManagerRef="secondaryTransactionManager",entityManagerFactoryRef="secondaryEntityManagerFactory",basePackages = "com.dz.ip.repository", queryLookupStrategy = Key.CREATE_IF_NOT_FOUND, repositoryBaseClass = BaseRepositoryImpl.class, repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class, repositoryImplementationPostfix = "Impl")
public class SecondaryJpaRepConfig {

	
	@Bean("secondaryDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.secondary")
	@Primary
	public DataSource secondaryDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean("secondaryEntityManagerFactory")
	@ConfigurationProperties(prefix = "spring.jpa.secondary")
	public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory(
			EntityManagerFactoryBuilder builder, JpaProperties jpaProperties ,@Qualifier("secondaryDataSource")DataSource datasource) {
		return builder
				.dataSource(datasource)
				.packages("com.dz.ip.entities")
				.properties(jpaProperties.getHibernateProperties(datasource))
				.persistenceUnit("ip").build();
	}
	@Bean("secondaryTransactionManager")
	public PlatformTransactionManager secondaryTransactionManager(
			@Qualifier("primaryEntityManagerFactory")EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

}
