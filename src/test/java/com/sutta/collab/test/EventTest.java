package com.sutta.collab.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sutta.collab.dao.EventDAO;
import com.sutta.collab.model.Event;


public class EventTest {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		
		context.scan("com.sutta.collab");
		context.refresh();
		
		Event event = (Event) context.getBean("event");
		EventDAO eventDAO = (EventDAO) context.getBean("eventDAO");
		
		event.setId("E01");
		event.setContent("Test Content");
		event.setDescription("TEST Description");
		
		eventDAO.save(event);
		
		context.close();
	}
	
}
