package com.dache.security.module.user.dao;

import com.dache.security.generic.dao.GenericDao;
import com.dache.security.module.user.domain.SystemUser;

public interface SystemUserDao extends GenericDao<SystemUser> {
	
	public SystemUser findByName(String name) throws Exception;

}
