/**
 * 
 */
package com.dz.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dz.entities.Authority;
import com.dz.entities.Role;
import com.dz.entities.User;
import com.dz.model.State;
import com.dz.model.Tree;
import com.dz.repository.AuthorityRepository;
import com.dz.repository.RoleRepository;
import com.dz.repository.UserReposity;

/**
 * 
 * @author qilizhi
 * @date 2016年7月12日 下午6:15:39
 */
@Service
@Transactional
public class RoleService {

	@Autowired
	private RoleRepository roleDao;
	@Autowired
	private AuthorityRepository authorityDao;
	@Autowired
	private UserReposity userReposity;

	public List<Role> findAll() {
		return roleDao.findAll();
	}

	public Role saveOrUpdate(Role r) {
		/*
		 * if (r.getParent() != null && r.getParent().getId() != null) { Role
		 * rp=roleDao.getOne(r.getParent().getId()); if(rp!=null)
		 * r.setParent(rp); }
		 */
		return roleDao.saveOrUpdate(r);
	}

	public Role findOne(Long id) {
		return roleDao.findOne(id);

	}
	

	/**
	 * 角色树
	 * 
	 */

	public List<Tree> getTree(List<Role> roleList) {
		List<Tree> roleTrees = new ArrayList<Tree>();
		for (Role role : roleList) {
			Tree AT = new Tree();
			if (role != null && role.getId() != null) {
				AT.setId(role.getId());
				AT.setText(role.getName());
				AT.setParentId(role.getParent() == null ? null : role
						.getParent().getId());
				//
				List<Role> roles = role.getChildren();
				if (!roles.isEmpty()) {
					AT.setChildren(getTree(roles));
				}
				roleTrees.add(AT);
			}

		}

		return roleTrees;
	}

	/**
	 * 获取所有角色树
	 * 
	 */

	public List<Tree> getAllTree() {
		List<Tree> ATs = new ArrayList<Tree>();
		List<Role> roles = roleDao.findByParentIdIsNull();
		if (roles != null && roles.size() > 0) {
			ATs = getTree(roles);
		}
		return ATs;

	}

	/**
	 * 标记树
	 * 
	 * 
	 */

	public List<Tree> tagTree(User user, List<Tree> roleTrees) {
		List<Tree> ATs = new ArrayList<Tree>();
		for (Tree at : roleTrees) {
			if (at != null) {
				for (Role RT : user.getRoles()) {
					if (RT != null && RT.getId() != null && at.getId() != null
							&& RT.getId() == at.getId()) {
						State st = new State();
						st.setChecked(true);
						at.setState(st);
						user.getRoles().remove(at);
					}
				}
			}
			if (at.getChildren() != null && at.getChildren().size() > 0) {
				at.setChildren(tagTree(user, at.getChildren()));
			}
			ATs.add(at);
		}

		return ATs;
	}

	/**
	 * @param id
	 */
	public void deleteByPrimaryKey(Long id) {
		roleDao.delete(id);
	}

	/**
	 * @param role
	 */
	public void insertSelective(Role role) {
		roleDao.save(role);
	}

	/**
	 * @param role
	 */
	public void updateByPrimaryKeySelective(Role role) {
		roleDao.save(role);

	}

	public void updateByDelete(Long roleId, List<Long> authorityIds) {
		List<Authority> authoritys = new ArrayList<Authority>();
		authoritys = authorityDao.findByIdIn(authorityIds);
		Role role = roleDao.findOne(roleId);
		role.getAuthorities().addAll(authoritys);
		roleDao.saveOrUpdate(role);

	}
	public void updateByUserId(Long userId, List<Long> roleIds) {
		List<Role> roles = new ArrayList<Role>();
		roles = roleDao.findByIdIn(roleIds);
		User user = userReposity.findOne(userId);
		user.getRoles().addAll(roles);
		userReposity.merge(user);
		//userReposity.flush();
		
	}

	/**
	 * @param roleId
	 * @param authorityIdsList
	 */
	public void batDelete(Long roleId, List<Long> authorityIdsList) {
		Role role = roleDao.findOne(roleId);
		List<Authority> authoritys = new ArrayList<Authority>();
		authoritys = authorityDao.findByIdIn(authorityIdsList);
		try {
			for (Authority authority : authoritys) {
				Iterator<Authority> authorityst = role.getAuthorities()
						.iterator();
				while (authorityst.hasNext()) {
					if (authority.getId() == authorityst.next().getId()) {
						authorityst.remove();
						continue;
					}
				}
			}
		} catch (Exception e) { 
			e.printStackTrace();
		}

		roleDao.saveOrUpdate(role);

	}
	
	public void batDeleteByUserId(Long userId, List<Long> roleIdsList) {
		User u = userReposity.findOne(userId);
		List<Role> roles =  roleDao.findByIdIn(roleIdsList);
		u.getRoles().removeAll(roles);
		/*
		try {
			for (Role role : roles) {
				for(Role r:rolesT){
					if(role.getId()==r.getId()) rolesT.remove(role);
				}
				Iterator<Role> rolest =u.getRoles().iterator();
				
				while (rolest.hasNext()) {
					if (role.getId() == rolest.next().getId()) {
						rolest.remove();
						continue;
					}
				}
			}
		} catch (Exception e) { 
			e.printStackTrace();
		}*/

		userReposity.merge(u);

	}

}
