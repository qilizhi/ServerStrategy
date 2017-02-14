package com.dz.repository;

import java.util.List;

import com.dz.base.repository.base.BaseRepository;
import com.dz.entities.Role;
/**
 * 
 * @author qilizhi
 * @date 2016年7月24日 下午5:43:23
 */
public interface RoleRepository extends BaseRepository<Role, Long> {


	/**
	 * @param id
	 * @return
	 */
	List<Role> findByParentId(Long id);

	/**
	 * @return
	 */
	List<Role> findByParentIdIsNull();

	List<Role> findByIdIn(List<Long> roleIds);


}
