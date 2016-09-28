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

import com.sutta.collab.dao.EventDAO;
import com.sutta.collab.model.Event;

@RestController
public class EventController {
	@Autowired
	Event event;

	@Autowired
	EventDAO eventDAO;

	@GetMapping("/Event/")
	public ResponseEntity<List<Event>> listAllEvent() {
		List<Event> listEvent = eventDAO.list();
		if (listEvent.isEmpty()) {

			return new ResponseEntity<List<Event>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Event>>(listEvent, HttpStatus.OK);
	}

	@GetMapping("/Event/{id}")
	public ResponseEntity<Event> getEvent(@PathVariable("id") String id) {
		event = eventDAO.get(id);
		if (event == null) {
			return new ResponseEntity<Event>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Event>(event, HttpStatus.OK);

	}

	@PostMapping("/Event/")
	public ResponseEntity<Void> createEvent(@RequestBody Event event,
			UriComponentsBuilder ucBuilder) {
		if (eventDAO.get(event.getId()) != null) {

			return new ResponseEntity<Void>(HttpStatus.CONFLICT);

		}

		/*
		 * role.setId("ROLE_USER"); role.setName("ROLE_USER");
		 */
		eventDAO.save(event);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("Event/{id}/").buildAndExpand(event.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

	}

	@PutMapping("/Event/{id}")
	public ResponseEntity<Event> updateEvent(@PathVariable("id") String id,
			@RequestBody Event event) {
		
		if (eventDAO.get(id) == null) {
			return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
		}

		event.setId(id);
		eventDAO.update(event);

		return new ResponseEntity<Event>(event, HttpStatus.OK);

	}

	@DeleteMapping("/Event/{id}")
	public ResponseEntity<Event> deleteEvent(@PathVariable("id") String id) {

		event = eventDAO.get(id);
		if (event == null) {

			return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
		}

		eventDAO.delete(id);
		return new ResponseEntity<Event>(HttpStatus.NO_CONTENT);
	}

	
	/* @DeleteMapping("/Event/") public ResponseEntity<Event>
	  deleteAllUsers(){
	 
	  eventDAO.deleteAllUsers(); return new
	  ResponseEntity<Event>(HttpStatus.NO_CONTENT); }*/
	 

}
