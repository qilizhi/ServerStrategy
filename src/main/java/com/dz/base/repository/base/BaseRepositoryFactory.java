package com.dz.base.repository.base;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

public class BaseRepositoryFactory<T, I extends Serializable> extends
		JpaRepositoryFactory {

	//private EntityManager entityManager;

	public BaseRepositoryFactory(EntityManager entityManager) {
		super(entityManager);
		//this.entityManager = entityManager;
	}

	/*protected Object getTargetRepository(
			JpaEntityInformation<T, ?> entityInformation) {
		return new BaseRepositoryImpl<T, I>(entityInformation, entityManager);
	}
*/
/*	@Override
	protected Object getTargetRepository(RepositoryInformation information) {
		return getTargetRepository(information, entityManager);
	}
*/
	/*protected Class<?> getRepositoryClass(RepositoryMetadata metadata) {
		return BaseRepositoryImpl.class;
	}*/
}