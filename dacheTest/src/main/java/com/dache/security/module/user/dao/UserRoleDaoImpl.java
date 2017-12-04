package com.dache.security.module.user.dao;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.dache.security.generic.dao.GenericDaoImpl;
import com.dache.security.module.user.domain.SystemUser;
import com.dache.security.module.user.domain.UserRole;

@Repository("UserRoleDaoImpl")
public class UserRoleDaoImpl extends GenericDaoImpl<UserRole> implements UserRoleDao{

	@Override
	public List<UserRole> getRoleByUser(SystemUser user) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("userId", user.getId());
		return queryByMap(map);
	}

}
