package com.sutta.collab.test;



import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import com.sutta.collab.dao.UserDetailsDAO;


import com.sutta.collab.model.UserDetails;

public class UserDetailsTest {
	
	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.sutta.collab");
		context.refresh();
		UserDetails userDetails = (UserDetails) context.getBean("userDetails");
		UserDetailsDAO userDetailsDAO = (UserDetailsDAO) context.getBean("userDetailsDAO");
		/*UserRole userRole = (UserRole) context.getBean("userRole");
		UserRoleDAO userRoleDAO = (UserRoleDAO) context.getBean("userRoleDAO");
		
		Role role = (Role) context.getBean("role");
		RoleDAO roleDAO = (RoleDAO) context.getBean("roleDAO");
		*/
		userDetails.setId("hridesh");
		userDetails.setPassword("hridesh");
		userDetails.setName("Hridesh");
		userDetails.setEmail("hrideshh@yahoo.com");
		userDetails.setMobile("9544151378");
		userDetails.setAddress("TCR");
		/*role.setName("ROLE_USER");
		role.setId("ROLE_USER");
		roleDAO.saveOrUpdate(role);
		
		Set<UserRole> userRoles = new HashSet<UserRole>();
		userRoles.add(userRole);
		userDetails.setUserRoles(userRoles);
		userRole.setRole(role);
		userRole.setUserDetails(userDetails);
		userRoleDAO.saveOrUpdate(userRole);
		*/
		
		userDetailsDAO.saveOrUpdate(userDetails);
		
		
		
			
	
		
	}

}
