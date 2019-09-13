package utilities;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.CommentDAO;
import dao.GroupDAO;
import dao.MessageDAO;
import dao.PostDAO;
import dao.StatusDAO;
import dao.TypeDAO;
import dao.UserDAO;
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
	
	public static void compile() {
		/**
		 * Does nothing but call the helper so the Application Context will be initialized. should be called when everything is setting up
		 */
	}
	
	public static void setInfoTables() {
		acon.getBean("statusDAO", StatusDAO.class).setUserStates();
		acon.getBean("statusDAO", StatusDAO.class).setPostStates();
		acon.getBean("statusDAO", StatusDAO.class).setGroupStates();
		acon.getBean("statusDAO", StatusDAO.class).setMessageStates();
		acon.getBean("typeDAO", TypeDAO.class).setPostTypes();
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

	public static UserDAO userDAO() {
		/**
		 * Returns the user DAO.
		 */
		return acon.getBean("userDAO", UserDAO.class);
	}
	
	public static PostDAO postDAO() {
		/**
		 * Returns the post DAO.
		 */
		return acon.getBean("postDAO", PostDAO.class);
	}
	
	public static GroupDAO groupDAO() {
		/**
		 * Returns the group DAO.
		 */
		return acon.getBean("groupDAO", GroupDAO.class);
	}
	
	public static MessageDAO messageDAO() {
		/**
		 * Returns the message DAO.
		 */
		return acon.getBean("messageDAO", MessageDAO.class);
	}
	
	public static CommentDAO commentDAO() {
		/**
		 * Returns the comment DAO.
		 */
		return acon.getBean("commentDAO", CommentDAO.class);
	}

}
