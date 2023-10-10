package com.app.services;

import java.util.List;

public interface IBaseService<T> {

	public void create(T t);
	
	public List<T> list();
	
	public T findById(Integer id);
	
	public void delete();
}
