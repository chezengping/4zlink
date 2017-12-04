package com.dache.security.module.user.service;

import java.util.List;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.dache.security.module.user.dao.UserRoleDao;
import com.dache.security.module.user.domain.SystemUser;
import com.dache.security.module.user.domain.UserRole;

@Service("UserRoleServiceImpl")
public class UserRoleServiceImpl implements UserRoleService{

	@Resource(name = "UserRoleDaoImpl")
	private UserRoleDao userRoleDao;
	
	@Transactional
	@Override
	public UserRole save(UserRole entity) throws Exception {
		return userRoleDao.save(entity);
	}

	@Transactional
	@Override
	public void delete(UserRole entity) throws Exception {
		userRoleDao.delete(entity);		
	}

	@Transactional
	@Override
	public UserRole update(UserRole entity) throws Exception {
		return userRoleDao.update(entity);
	}

	
	@Override
	public List<UserRole> findAll() throws Exception {
		return userRoleDao.findAll();
	}

	@Override
	public UserRole findById(Object id) throws Exception {
		return userRoleDao.findById(id);
	}

	@Transactional
	@Override
	public void clearTable() throws Exception {
		userRoleDao.clearTable();		
	}

	@Override
	public List<UserRole> getRoleByUser(SystemUser user) throws Exception {
		return userRoleDao.getRoleByUser(user);
	}

}
