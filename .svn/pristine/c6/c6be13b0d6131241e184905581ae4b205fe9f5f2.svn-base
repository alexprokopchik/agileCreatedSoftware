/**
 * 
 */
package test;

import java.io.File;
import java.io.FileNotFoundException;

import model.Conference;
import model.Paper;
import model.User;

import org.junit.Before;
import org.junit.Test;

import controller.FileWriter;
import controller.SerializableController;

/**
 * @author Janelle
 *
 */
public class SerializableTest {
	private Paper p;
	private User u;
	private Conference c;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		p = new Paper(new File("test1.txt"),"Janelle", new Conference());
		c = new Conference();
		u = new User("janelle", 1, c);
		
		FileWriter.writeSerializable(p, p.getFileSerialName());
		FileWriter.writeSerializable(u, u.getFileSerialName());
	}

	@Test
	public void test() {
		Paper q = null;
		User v = null;
		
		try {
			q = (Paper) FileWriter.deSerializable("P_" + p.getFileSerialName());
			v = (User) FileWriter.deSerializable("U_" + u.getFileSerialName());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		assert(u.getUserName().equals(v.getUserName()));
		assert(q.getAuthor().equals(p.getAuthor()));
		assert(q.getPaper().equals(p.getPaper()));
		
	}
	
	@Test
	public void test1() {
		u.addObserver(new SerializableController());
		u.addPaper(new Paper(new File("test.txt"), "bob", c));
		User u1 = null;
		User u2 = null;
		
		try {
			u1 = (User) FileWriter.deSerializable(u.getFileSerialName());
			u2 = (User) FileWriter.deSerializable(u.getFileSerialName());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		assert(u1 != null);
		assert(u2 != null);
		assert(u1.getPaper().size() != u2.getPaper().size());		
	}

}
