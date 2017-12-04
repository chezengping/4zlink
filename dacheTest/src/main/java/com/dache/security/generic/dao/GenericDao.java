package com.dache.security.generic.dao;

import java.util.HashMap;
import java.util.List;

public interface GenericDao<T>  {

	public T save(T entity) throws Exception ;  
	  
    public void delete(T entity) throws Exception ;  
    
    public T update(T entity) throws Exception ;  
  
    public List<T> findAll () throws Exception;  
    
    public T findById(Object id) throws Exception ;  
    
    public List<T> queryByString(String sql) throws Exception ;
    
    public List<T> queryByMap (HashMap<String, Object> map)  throws Exception;
	
    public void clearTable() throws Exception;
}
