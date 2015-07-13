package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;

import model.Conference;
import model.Paper;
import model.User;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserTest {
	User u;
	File f;
	Conference c;

	@Before
	public void setUp() throws Exception {
		u = new User("test@email.com", 1, new Conference());
		f = new File("test.txt");
		c = new Conference();
	}

	@Test
	public void testGetPermissions() {
		assertEquals(1, u.getPermissions());
	}

	@Test
	public void testSetPermssions() {
		u.setPermssions(5);
		assertEquals(5, u.getPermissions());
	}

	@Test
	public void testGetUserName() {
		assertTrue("test@email.com".equals(u.getUserName()));
	}

	@Test
	public void testAddPaper() {
		int size = u.getPaper().size();
		u.addPaper(new Paper(f, "test", c));
		assertTrue(u.getPaper().size() > size);
	}

	@Test
	public void testRemovePaper() {
		Paper p1 = new Paper(f, "test", c);
		Paper p2 = new Paper(f, "test", c);
		Paper p3 = new Paper(f, "test", c);
		
		u.addPaper(p1);
		u.addPaper(p2);
		u.addPaper(p3);
		
		int size = u.getPaper().size();
		u.removePaper(p2);
		assertTrue(u.getPaper().size() < size);
		assertFalse(u.getPaper().contains(p2));
	}
	
	@Test(expected=Exception.class)
	public void testChangePermission() throws Exception {
		u.changePermission(new User("test", 8, new Conference()), 4);
		fail();
	}
}
