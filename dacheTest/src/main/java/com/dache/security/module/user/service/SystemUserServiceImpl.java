package com.dache.security.module.user.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.dache.security.module.user.dao.SystemUserDao;
import com.dache.security.module.user.domain.SystemUser;

@Service("SystemUserServiceImpl")
public class SystemUserServiceImpl implements SystemUserService{

	@Resource(name = "SystemUserDaoImpl")
	private SystemUserDao systemUserDao;
	
	@Transactional
	@Override
	public SystemUser save(SystemUser entity) throws Exception {
		return systemUserDao.save(entity);
	}

	@Transactional
	@Override
	public void delete(SystemUser entity) throws Exception {
		systemUserDao.delete(entity);		
	}

	@Transactional
	@Override
	public SystemUser update(SystemUser entity) throws Exception {
		return systemUserDao.update(entity);
	}

	@Override
	public List<SystemUser> findAll() throws Exception {
		return systemUserDao.findAll();
	}

	@Override
	public SystemUser findById(Object id) throws Exception {
		return systemUserDao.findById(id);
	}

	@Transactional
	@Override
	public void clearTable() throws Exception {
		systemUserDao.clearTable();		
	}

	@Override
	public SystemUser findByName(String name) throws Exception {
		return systemUserDao.findByName(name);
	}

}
