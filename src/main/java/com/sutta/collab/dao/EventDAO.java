package com.sutta.collab.dao;

import java.util.List;

import com.sutta.collab.model.Event;

public interface EventDAO {
	
	public List<Event> list();
	
	public Event get(String id);
	
	public boolean save(Event event);
	
	public boolean update(Event event);
	
	public boolean delete(String id);
}
