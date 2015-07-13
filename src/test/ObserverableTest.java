/**
 * 
 */
package test;

import java.io.File;

import model.AccountDatabase;
import model.Conference;
import model.Paper;
import model.User;

import org.junit.Before;
import org.junit.Test;

import controller.FileWriter;

/**
 * @author Janelle
 *
 */
public class ObserverableTest {

	AccountDatabase actDB;
	File f1, f2, f3;
	Conference c;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		actDB = (AccountDatabase) FileWriter.deSerializable("AccountDatabase.ser");
		f1 = new File(getClass().getResource("test1.txt").toURI());
		f2 = new File(getClass().getResource("test2.txt").toURI());
		f3 = new File(getClass().getResource("test3.txt").toURI());
		c = new Conference();
		c.setConferenceId(1);
	}

	@Test
	public void test() {
		User u = actDB.getUser(actDB.getAccounts().get("janelletin@yahoo.com"), 1);

		u.addPaper(new Paper(f1, u.getUserName(), c));
		
		FileWriter.writeSerializable(u, u.getFileSerialName());
		FileWriter.writeSerializable(actDB, "AccountDatabase.ser");
	}
	@Test
	public void test1() {
		User u = actDB.getUser(actDB.getAccounts().get("janelletin@yahoo.com"), 1);
	}
}
