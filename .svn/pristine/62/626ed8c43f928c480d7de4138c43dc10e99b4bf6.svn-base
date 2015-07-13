package test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import org.junit.Before;
import org.junit.Test;

import controller.DatabaseConstructor;

public class DatabaseConstructorTest {
	File f1;
	File f2;
	DatabaseConstructor dbCon;
	
	@Before
	public void setUp() throws Exception {		
		// a file with one conference and multiple users
		f1 = new File(getClass().getResource("test1.txt").toURI());
		// a file with multiple conferences and multiple users w/ different roles
		f2 = new File(getClass().getResource("test2.txt").toURI());

		/*PrintWriter writer = new PrintWriter(new File(getClass().getResource("hi.txt").toURI()), "UTF-8");
		writer.println("hi hello");
		writer.close();*/
		
		dbCon = new DatabaseConstructor();
	}

	@Test
	public void test1() {
		try {
			dbCon.createDatabases(f1);
			// correct number of accounts and conferences 
			assertTrue(dbCon.actDatabase.getAccounts().keySet().size() == 5);
			assertTrue(dbCon.conDatabase.getConferences().keySet().size() == 4);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test2() {
		try {
			dbCon.createDatabases(f2);
			// correct entries
			assertTrue(dbCon.actDatabase.getAccounts().keySet().size() == 5);
			assertTrue(dbCon.conDatabase.getConferences().keySet().size() == 4);
			
			assertTrue(dbCon.actDatabase.getAccounts().get("janelletin@yahoo.com").getUserConfId(1).getPermissions()== 15); // permission on user con1
			assertTrue(dbCon.actDatabase.getAccounts().get("janelletin@yahoo.com").getPermissions() == 15); // permission on account
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
