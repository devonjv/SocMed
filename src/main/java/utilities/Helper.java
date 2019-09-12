package utilities;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.StatusService;
import service.TypeService;

public class Helper {
	/**
	 * Deigned to help the services. Initializes the Application Context once, and
	 * allows you to pull the beans at will.
	 * 
	 * Basically load once, use whenever.
	 */

	private final static ApplicationContext acon = new ClassPathXmlApplicationContext("ApplicationContext.xml");

	private Helper() {
	}

	public static ApplicationContext getContext() {
		/**
		 * Returns the application context, in the event of extra needs down the line.
		 */
		return acon;
	}

	public static StatusService statusService() {
		/**
		 * Returns the status service.
		 */
		return acon.getBean("statusService", StatusService.class);
	}

	public static TypeService typeService() {
		/**
		 * Returns the type service.
		 */
		return acon.getBean("typeService", TypeService.class);
	}

}
