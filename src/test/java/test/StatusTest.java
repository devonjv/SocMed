package test;

import static org.junit.Assert.assertEquals;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import dao.StatusDAOImpl;

public class StatusTest {
	
	@Autowired
	private SessionFactory sf;

	@Test
	public void setUserStates() {
		
		StatusDAOImpl sImpl = new StatusDAOImpl();
		assertEquals("Setting Active User Status", "ACTIVE", sImpl.getActiveUser());
		assertEquals("Setting Admin User Status", "ADMIN", sImpl.getAdminUser());
		assertEquals("Setting Banned User Status", "BANNED", sImpl.getBannedUser());
	}

	@Test
	public void setPostStates() {
		
		StatusDAOImpl sImpl = new StatusDAOImpl();
		assertEquals("Setting Public Post Status", "PUBLIC", sImpl.getPublicPost());
		assertEquals("Setting Private Post Status", "PRIVATE", sImpl.getPrivatePost());
		assertEquals("Setting Group Post Status", "GROUP", sImpl.getGroupPost());
		assertEquals("Setting Banned Post Status", "BANNED", sImpl.getBannedPost());
	}

	@Test
	public void setGroupStates() {
		
		StatusDAOImpl sImpl = new StatusDAOImpl();
		assertEquals("Setting Public Group Status", "PUBLIC", sImpl.getPublicGroup());
		assertEquals("Setting Private Group Status", "PRIVATE", sImpl.getPrivateGroup());
		assertEquals("Setting Banned Group Status", "BANNED", sImpl.getBannedGroup());
	}
	
	@Test
	public void setMessageStates() {
		
		StatusDAOImpl sImpl = new StatusDAOImpl();
		assertEquals("Setting Visible Message Status", "VISIBLE", sImpl.getVisibleMessage());
		assertEquals("Setting Hiden Message Status", "HIDDEN", sImpl.getHiddenMessage());
	}
}
