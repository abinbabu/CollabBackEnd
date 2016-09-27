package com.sutta.collab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sutta.collab.dao.BlogDAO;
import com.sutta.collab.model.Blog;

@RestController
public class BlogController {
	@Autowired
	Blog blog;

	@Autowired
	BlogDAO blogDAO;

	@GetMapping("/Blog/")
	public ResponseEntity<List<Blog>> listAllBlog() {
		List<Blog> listBlog = blogDAO.list();
		if (listBlog.isEmpty()) {

			return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Blog>>(listBlog, HttpStatus.OK);
	}

	@GetMapping("/Blog/{id}")
	public ResponseEntity<Blog> getBlog(@PathVariable("id") String id) {
		blog = blogDAO.get(id);
		if (blog == null) {
			return new ResponseEntity<Blog>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Blog>(blog, HttpStatus.OK);

	}

	@PostMapping("/Blog/")
	public ResponseEntity<Void> createBlog(@RequestBody Blog blog,
			UriComponentsBuilder ucBuilder) {
		if (blogDAO.get(blog.getId()) != null) {

			return new ResponseEntity<Void>(HttpStatus.CONFLICT);

		}

		/*
		 * role.setId("ROLE_USER"); role.setName("ROLE_USER");
		 */
		blogDAO.save(blog);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("Blog/{id}/").buildAndExpand(blog.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

	}

	@PutMapping("/Blog/{id}")
	public ResponseEntity<Blog> updateBlog(@PathVariable("id") String id,
			@RequestBody Blog blog) {
		
		if (blogDAO.get(id) == null) {
			return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
		}

		
		blogDAO.update(blog);

		return new ResponseEntity<Blog>(blog, HttpStatus.OK);

	}

	@DeleteMapping("/Blog/{id}")
	public ResponseEntity<Blog> deleteBlog(@PathVariable("id") String id) {

		blog = blogDAO.get(id);
		if (blog == null) {

			return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
		}

		blogDAO.delete(id);
		return new ResponseEntity<Blog>(HttpStatus.NO_CONTENT);
	}

	
	/* @DeleteMapping("/Blog/") public ResponseEntity<Blog>
	  deleteAllUsers(){
	 
	  blogDAO.deleteAllUsers(); return new
	  ResponseEntity<Blog>(HttpStatus.NO_CONTENT); }*/
	 

}
