package com.sutta.collab.dao;

import java.util.List;

import com.sutta.collab.model.UserDetails;

public interface UserDetailsDAO {
	
	public List<UserDetails> list();
	
	public boolean saveOrUpdate(UserDetails userDetails);
	
	public UserDetails get(String id);
	
	public boolean delete(String id);

}
