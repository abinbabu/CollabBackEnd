package com.sutta.collab.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sutta.collab.dao.UserDetailsDAO;
import com.sutta.collab.model.UserDetails;

@Repository("userDetailsDAO")
public class UserDetailsDAOImpl implements UserDetailsDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public UserDetailsDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<UserDetails> list() {
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<UserDetails> list = (List<UserDetails>) sessionFactory.getCurrentSession()
				.createCriteria(UserDetails.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return list;
	}
	
	
	
	@Transactional
	public UserDetails get(String id) {
		String hql = "from UserDetails where id= '" + id + "'";
		@SuppressWarnings("unchecked")
		Query<UserDetails> query = sessionFactory.getCurrentSession().createQuery(hql);
		List<UserDetails> listUserDetails = query.getResultList();

		if (listUserDetails != null && !listUserDetails.isEmpty()) {
			return listUserDetails.get(0);
		}

		return null;
	}
	
	@Transactional
	public boolean delete(String id) {
		UserDetails userDelete = new UserDetails();

		userDelete.setId(id);
		try {
			sessionFactory.getCurrentSession().delete(userDelete);
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		
		return true;

	}

	@Transactional
	public boolean save(UserDetails userDetails) {
		try {
			sessionFactory.getCurrentSession().save(userDetails);
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Transactional
	public boolean update(UserDetails userDetails) {
		try {
			sessionFactory.getCurrentSession().update(userDetails);
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
