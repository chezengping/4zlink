package com.dache.security.generic.dao;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.dache.security.module.user.domain.SystemUser;
import com.dache.security.module.user.domain.UserRole;

@SuppressWarnings(value = { "unchecked", "unused" })
@Repository("GenericDaoImpl")
public class GenericDaoImpl<T> implements GenericDao<T> {

	@PersistenceContext
	protected EntityManager em;
	
	private Class<T> persistentClass;

	public GenericDaoImpl() {
	}

	public Class<T> getPersistentClass() {
		return this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	@Override
	public T save(T entity) throws Exception {
		em.persist(entity);
		return (T) entity;
	}

	@Override
	public void delete(T entity) throws Exception {
		Object object = em.merge(entity);
		em.remove(object);
	}

	@Override
	public T update(T entity) throws Exception {
		Object object = em.merge(entity);
		return (T) object;
	}

	@Override
	public List<T> findAll() throws Exception {
		List<T> resultList = null;
		String sql = "from " + getPersistentClass().getName() + " obj ";
		TypedQuery<T> query = em.createQuery(sql, this.getPersistentClass());
		resultList = query.getResultList();
		return resultList;
	}

	@Override
	public T findById(Object id) throws Exception {
		return (T) em.find(this.getPersistentClass(), id);
	}

	@Override
	public List<T> queryByString(String sql) throws Exception {
		List<T> resultList = null;
		resultList = (List<T>) em.createQuery(sql).getResultList();
		return resultList;
	}

	@Override
	public List<T> queryByMap(HashMap<String, Object> map) throws Exception {
		List<T> resultList = null;
//		StringBuilder sql = new StringBuilder("SELECT o from " + getPersistentClass().getName() + " o WHERE ");
//		StringBuilder conStr = new StringBuilder("");
//		// 拼接条件
//		for (String key : map.keySet()) {
//			Object value = map.get(key);
//			if (value instanceof List) {
//				conStr = conStr.append(String.format(" o.%s in (:%s) and", key, key));
//			} else {
//				conStr = conStr.append(String.format(" o.%s = :%s and", key, key));
//			}
//		}
//		// 去掉最后一个and
//		sql.append(conStr.toString().substring(0, conStr.length() - 4));
//		Query query = em.createQuery(sql.toString());
//		for (String key : map.keySet()) {
//			query = query.setParameter(key, map.get(key));
//		}
//		resultList = query.getResultList();
		
		//////////////////////////////以下是自己为了跑通系统加的代码////////////////////////////////
		Object userName = map.get("userName");
		if(userName!=null){
			userName=userName.toString();
			List<SystemUser> users=new ArrayList<SystemUser>();
			SystemUser systemUser=new SystemUser();
			if(userName.equals("user")){
				systemUser.setUserName("user");
				systemUser.setPassword("user");
				systemUser.setId(2L);
			}else if (userName.equals("admin")) {
				systemUser.setUserName("admin");
				systemUser.setPassword("admin");
				systemUser.setId(1L);
			}
			users.add(systemUser);
			resultList=(List<T>) users;
		}
		Object userId = map.get("userId");
		if(userId!=null){
			userId=userId.toString();
			List<UserRole> roles=new ArrayList<UserRole>();
			if(userId.equals("1")){
//				UserRole userRole1=new UserRole();
//				userRole1.setUserId(1L);
//				userRole1.setRole("user");
//				roles.add(userRole1);
				UserRole userRole2=new UserRole();
				userRole2.setUserId(1L);
				userRole2.setRole("admin");
				roles.add(userRole2);
			}else if(userId.equals("2")){
				UserRole userRole=new UserRole();
				userRole.setUserId(2L);
				userRole.setRole("user");
				roles.add(userRole);
			}
			resultList=(List<T>) roles;
		}
		
		return resultList;
	}

	@Override
	public void clearTable() throws Exception {
		String sql = "DELETE FROM " + getPersistentClass().getName();
		Query q = em.createQuery(sql);
		q.executeUpdate();
	}

}
