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

import com.sutta.collab.dao.UserDetailsDAO;

import com.sutta.collab.model.UserDetails;


@RestController
public class UserDetailsController {
	
	@Autowired
	UserDetails userDetails;
	
	@Autowired
	UserDetailsDAO userDetailsDAO;

	
	@GetMapping("/UserDetails")
	public ResponseEntity<List<UserDetails>> listAllUserDetails(){
		List<UserDetails> listUserDetails = userDetailsDAO.list();
		if(listUserDetails.isEmpty()){
			
			return new ResponseEntity<List<UserDetails>>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<UserDetails>>(listUserDetails, HttpStatus.OK);
	}
	
	@GetMapping("/UserDetails/{id}")
	public ResponseEntity<UserDetails> getUserDetails(@PathVariable("id")String id) {
		
		 userDetails = userDetailsDAO.get(id);
		 
		 if(userDetails == null) {
			 return new ResponseEntity<UserDetails>(HttpStatus.NOT_FOUND);
		 }
		 
		 return new ResponseEntity<UserDetails>(userDetails, HttpStatus.OK);
	}
	
	@PostMapping("/UserDetails/")
	public ResponseEntity<Void> createUserDetails(@RequestBody UserDetails userDetails, UriComponentsBuilder ucBuilder)  {
		if(userDetailsDAO.get(userDetails.getId())!=null) {
			
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			
		}
		
		userDetailsDAO.save(userDetails);
		
	/*	role.setId("ROLE_USER");
		role.setName("ROLE_USER");*/
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("UserDetails/{id}/").buildAndExpand(userDetails.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("/UserDetails/{id}")
	public ResponseEntity<UserDetails> updateUserDetails(@PathVariable("id")String id,@RequestBody UserDetails userDetails) {
		
		if(userDetailsDAO.get(id) == null) {
			return new ResponseEntity<UserDetails>(HttpStatus.NOT_FOUND);
		}
		
	
		
		userDetailsDAO.update(userDetails);
		
		return new ResponseEntity<UserDetails>(userDetails, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/UserDetails/{id}")
	public ResponseEntity<UserDetails> deleteUserDetails(@PathVariable("id") String id) {
		
		userDetails = userDetailsDAO.get(id);
		if(userDetails == null) {
			
			return new ResponseEntity<UserDetails>(HttpStatus.NOT_FOUND);
		}
		
		userDetailsDAO.delete(id);
		return new ResponseEntity<UserDetails>(HttpStatus.NO_CONTENT);
	}
	
/*	@DeleteMapping("/UserDetails/")
	public ResponseEntity<UserDetails> deleteAllUsers(){
		
		userDetailsDAO.deleteAllUsers();
		return new ResponseEntity<UserDetails>(HttpStatus.NO_CONTENT);
	}
*/	
	
    
	
}
