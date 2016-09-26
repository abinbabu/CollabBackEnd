package com.sutta.collab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sutta.collab.dao.BlogDAO;
import com.sutta.collab.model.Blog;

@RestController
public class BlogController {
	@Autowired
	Blog blog;
	
	@Autowired
	BlogDAO blogDAO;
	
	@GetMapping("/Blog/")
	public ResponseEntity<List<Blog>> listAllBlog(){
		List<Blog> listBlog = blogDAO.list();
		if(listBlog.isEmpty()){
			
			return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Blog>>(listBlog, HttpStatus.OK);
	}
	

}
