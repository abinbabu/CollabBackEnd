package com.sutta.collab.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sutta.collab.dao.EventDAO;
import com.sutta.collab.model.Event;

@Repository("eventDAO")
public class EventDAOImpl implements EventDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public EventDAOImpl(SessionFactory sessionFactory) {
		
		
		this.sessionFactory = sessionFactory;
		
	
	}
	
	

	@Transactional
	public List<Event> list() {
		
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<Event> list = sessionFactory.getCurrentSession().createCriteria(Event.class)
				          .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		
		return list;
	}
	@Transactional
	public Event get(String id) {
		
		String hql = "from Event where id = '" + id + "'";
		
		@SuppressWarnings("unchecked")
		Query<Event> query = sessionFactory.getCurrentSession().createQuery(hql);
		
		List<Event> listEvent = query.getResultList();
		
		if(listEvent != null && !listEvent.isEmpty()) {
			
			return listEvent.get(0);
		}
	
		return null;
	}
	



	@Transactional
	public boolean delete(String id) {
	  
	
      Event deleteEvent = new Event();
      
      deleteEvent.setId(id);
      
      try {
		sessionFactory.getCurrentSession().delete(deleteEvent);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
      
     
      return true;
		
	}



	@Transactional
	public boolean save(Event event) {
	
		
		try {
			sessionFactory.getCurrentSession().save(event);
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return false;
		}
	
		return true;
	}


	@Transactional
	public boolean update(Event event) {

		try {
			sessionFactory.getCurrentSession().update(event);
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return false;
		}
	
		return true;
	}

}
