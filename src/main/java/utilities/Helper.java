package utilities;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.StatusService;
import service.TypeService;

public class Helper {
	/**
	 * Deigned to help the services.
	 */
	
	private final static ApplicationContext acon = new ClassPathXmlApplicationContext("ApplicationContext.xml");
	
	private Helper() {
	}
	
	public static StatusService statusService() {
		return acon.getBean("statusService",StatusService.class);
	}
	
	public  static TypeService typeService() {
		return acon.getBean("typeService",TypeService.class);
	}
}
