package com.dache.security.module.user.service;

import com.dache.security.generic.service.GenericService;
import com.dache.security.module.user.domain.SystemUser;

public interface SystemUserService extends GenericService<SystemUser>{
	
	public SystemUser findByName(String name) throws Exception;
}
