package com.dz.repository;

import java.util.List;

import com.dz.base.repository.base.BaseRepository;
import com.dz.entities.User;


/**
 * Spring data jpa 自动实现的接口
 * 
 * @author qlz
 *
 */

public interface UserReposity extends BaseRepository<User, Long> {

	public List<User> findByEmail(String email);
	public User findOneByUsername(String name);
	public List<User> findByName(String name);
	public List<User> findByUsername(String name);
}
