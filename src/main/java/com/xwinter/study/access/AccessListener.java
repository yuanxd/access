package com.xwinter.study.access;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AccessListener implements ServletContextListener {
	public static final String BASE_PACKAGE = "accessBasePackage";

	public void contextInitialized(ServletContextEvent sce) {
		AnnotationProcess process = new AnnotationProcess();
		String basePackage = sce.getServletContext().getInitParameter(
				BASE_PACKAGE);
		process.scan(basePackage);
	}

	public void contextDestroyed(ServletContextEvent sce) {

	}

}
