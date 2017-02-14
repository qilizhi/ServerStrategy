package com.dz.base.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.dz.base.repository.base.BaseRepository;

public abstract class BaseServciceImpl<T, ID extends Serializable> {

	@Autowired
	protected BaseRepository<T, ID> baseRepository;

	public List<T> findAll() {
		return baseRepository.findAll();
	}

	public List<T> findAll(Sort sort) {
		return baseRepository.findAll(sort);
	}

	public List<T> findAll(Iterable<ID> ids) {
		return baseRepository.findAll(ids);
	}

	public <S extends T> List<S> save(Iterable<S> entities) {
		return baseRepository.save(entities);
	}

	public void flush() {
		baseRepository.flush();

	}

	public <S extends T> S saveAndFlush(S entity) {
		return baseRepository.saveAndFlush(entity);
	}

	public void deleteInBatch(Iterable<T> entities) {
		baseRepository.deleteInBatch(entities);

	}

	public void deleteAllInBatch() {
		baseRepository.deleteAllInBatch();

	}

	public T getOne(ID id) {
		return baseRepository.getOne(id);
	}

	public <S extends T> List<S> findAll(Example<S> example) {
		return baseRepository.findAll(example);
	}

	public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
		return baseRepository.findAll(example, sort);
	}

	public Page<T> findAll(Pageable pageable) {
		return baseRepository.findAll(pageable);
	}

	public <S extends T> S save(S entity) {
		return baseRepository.save(entity);
	}

	public T findOne(ID id) {
		return baseRepository.findOne(id);
	}

	public boolean exists(ID id) {
		return baseRepository.exists(id);
	}

	public long count() {
		return baseRepository.count();
	}

	public void delete(ID id) {
		baseRepository.delete(id);
	}

	public void delete(T entity) {
		baseRepository.delete(entity);
	}

	public void delete(Iterable<? extends T> entities) {
		baseRepository.delete(entities);
	}

	public void deleteAll() {
		baseRepository.deleteAll();

	}

	public <S extends T> S findOne(Example<S> example) {
		return baseRepository.findOne(example);
	}

	public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
		return baseRepository.findAll(example, pageable);
	}

	public <S extends T> long count(Example<S> example) {
		return baseRepository.count(example);
	}

	public <S extends T> boolean exists(Example<S> example) {
		return baseRepository.exists(example);
	}

	public <S extends T> boolean exists(S s) {
		return baseRepository.exists(Example.of(s));
	}

	public T findOne(Specification<T> spec) {
		return baseRepository.findOne(spec);
	}

	public List<T> findAll(Specification<T> spec) {
		return baseRepository.findAll(spec);
	}

	public Page<T> findAll(Specification<T> spec, Pageable pageable) {
		return baseRepository.findAll(spec, pageable);
	}

	public List<T> findAll(Specification<T> spec, Sort sort) {
		return baseRepository.findAll(spec, sort);
	}

	public long count(Specification<T> spec) {
		return baseRepository.count(spec);
	}

	public <S extends T> S merge(S entity) {
		return baseRepository.merge(entity);
	}

	public <S extends T> S saveOrUpdate(S entity) {
		return baseRepository.saveOrUpdate(entity);
	}

}