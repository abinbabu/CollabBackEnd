package com.sutta.collab.daoImpl;

import java.util.List;

import org.hibernate.Criteria;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sutta.collab.dao.BlogDAO;
import com.sutta.collab.model.Blog;

@Repository("blogDAO")
public class BlogDAOImpl implements BlogDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public BlogDAOImpl(SessionFactory sessionFactory) {
		
		
		this.sessionFactory = sessionFactory;
		
	
	}
	
	

	@Transactional
	public List<Blog> list() {
		
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<Blog> list = sessionFactory.getCurrentSession().createCriteria(Blog.class)
				          .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		
		return list;
	}
	@Transactional
	public Blog get(String id) {
		
		String hql = "from Blog where id = '" + id + "'";
		
		@SuppressWarnings("unchecked")
		Query<Blog> query = sessionFactory.getCurrentSession().createQuery(hql);
		
		List<Blog> listBlog = query.getResultList();
		
		if(listBlog != null && !listBlog.isEmpty()) {
			
			return listBlog.get(0);
		}
	
		return null;
	}
	

	@Transactional
	public boolean saveOrUpdate(Blog blog) {

      try {
		sessionFactory.getCurrentSession().saveOrUpdate(blog);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
		return true;
	}


	@Transactional
	public boolean delete(String id) {
	  
	
      Blog deleteBlog = new Blog();
      
      deleteBlog.setId(id);
      
      try {
		sessionFactory.getCurrentSession().delete(deleteBlog);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
      
     
      return true;
		
	}

}
