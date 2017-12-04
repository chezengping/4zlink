package com.dache.security.module.user.service;

import java.util.List;

import com.dache.security.generic.service.GenericService;
import com.dache.security.module.user.domain.SystemUser;
import com.dache.security.module.user.domain.UserRole;

public interface UserRoleService extends GenericService<UserRole> {
	public List<UserRole> getRoleByUser(SystemUser user) throws Exception; 
}
