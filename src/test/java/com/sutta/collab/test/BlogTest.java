package com.sutta.collab.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sutta.collab.dao.BlogDAO;
import com.sutta.collab.model.Blog;


public class BlogTest {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		
		context.scan("com.sutta.collab");
		context.refresh();
		
		Blog blog = (Blog) context.getBean("blog");
		BlogDAO blogDAO = (BlogDAO) context.getBean("blogDAO");
		
		blog.setId("B013");
		blog.setName("NewB");
		blog.setDescription("TEST");
		
		blogDAO.save(blog);
		
		context.close();
	}
	
}
