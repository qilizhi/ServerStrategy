package com.dz.repository;

import java.util.List;

import com.dz.base.repository.base.BaseRepository;
import com.dz.entities.Authority;

/**
 * 
 * @author qilizhi
 * @date 2016年7月24日 下午5:43:58
 */
public interface AuthorityRepository extends BaseRepository<Authority, Long> {

	/**
	 * @param id
	 * @return
	 */
	public List<Authority> findByParentId(Long id);

	/**
	 * @return
	 */
	public List<Authority> findByParentIdIsNull();
	public List<Authority> findByIdIn(String[] idArray);
	public List<Authority> findByIdIn(List<Long> ids);



}
