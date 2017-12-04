package com.dache.security.module.user.dao;

import java.util.List;
import com.dache.security.generic.dao.GenericDao;
import com.dache.security.module.user.domain.SystemUser;
import com.dache.security.module.user.domain.UserRole;


public interface UserRoleDao extends GenericDao<UserRole>{
	public List<UserRole> getRoleByUser(SystemUser user) throws Exception;
}
