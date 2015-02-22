package com.service.upemgur.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;

public class Utils {

	public Utils() {
	}
	
	public  static String ADMIN_ROLE = "ROLE_ADMIN";

	public static String getUsername() {

		if (SecurityContextHolder.getContext().getAuthentication() == null){
			return "";
		}
		// Get the DB contact
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		}
		String username = userDetails.getUsername();

		return username;
	}
	
	public static boolean ifUserAdmin(HttpServletRequest request) {
		
		if (request instanceof SecurityContextHolderAwareRequestWrapper) {
            SecurityContextHolderAwareRequestWrapper requestWrapper = (SecurityContextHolderAwareRequestWrapper) request;
            return requestWrapper.isUserInRole(ADMIN_ROLE);
            
        }
		return false;

		
	}
	

}
