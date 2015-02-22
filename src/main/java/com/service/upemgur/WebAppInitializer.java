package com.service.upemgur;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * This class replaces the "old" web.xml and is automatically scanned at the application startup
 */
public class WebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		final XmlWebApplicationContext appContext = new XmlWebApplicationContext();
		appContext.getEnvironment().setActiveProfiles("resthub-jpa", "resthub-web-server");
		final String[] locations = { "classpath*:resthubContext.xml", "classpath*:applicationContext.xml" ,"classpath*:spring-security.xml"  };
		appContext.setConfigLocations(locations);


		//Add security filter
		servletContext.addFilter("springSecurityFilterChain", org.springframework.web.filter.DelegatingFilterProxy.class).addMappingForUrlPatterns(null, false, "/*");

		
		//Add entity filter
		servletContext.addFilter("Spring OpenEntityManagerInViewFilter", org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter.class).addMappingForUrlPatterns(null, false, "/*");

		final ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(appContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/*");

		servletContext.addListener(new ContextLoaderListener(appContext));
	}
}
