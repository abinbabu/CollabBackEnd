package com.sutta.collab.dao;

import java.util.List;

import com.sutta.collab.model.Blog;

public interface BlogDAO {
	
	public List<Blog> list();
	
	public Blog get(String id);
	
	public boolean saveOrUpdate(Blog blog);
	
	public boolean delete(String id);
}
