package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import model.AccountDatabase;
import model.Conference;
import model.Paper;
import model.User;

import org.junit.Before;
import org.junit.Test;

import controller.FileWriter;
import controller.SerializableController;

public class FileWriterTest {
		File f1;
		File f2;
		File f3;
		User u;
		Paper p;
		Conference c;
		
	@Before
	public void setUp() throws Exception {
		f1 = new File(getClass().getResource("test1.txt").toURI());
		f2 = new File(getClass().getResource("test2.txt").toURI());
		f3 = new File(getClass().getResource("test3.txt").toURI());
		c = new Conference();
		c.setConferenceId(1);
		p = new Paper(f1, "Harmeet Singh", c);
		u = new User("hs@yahoo.com", 1, c);
		u.addPaper(p);
	}

	@Test
	public void PaperTest() {
		FileWriter.writePaperFile(f1, u);
	}
	
	@Test
	public void ReviewTest() {
		FileWriter.writeReviewFile(f2, u, p);
	}
	
	@Test
	public void RecommendationTest() {
		FileWriter.writeRecommendationFile(f3, u, p);
	}
	
	@Test
	public void serializeTest() {
		FileWriter.writeSerializable(p, "test.ser");
		Path p = Paths.get("test.ser");
		if (Files.exists(p) == Files.notExists(p)) {
			assertTrue(Files.exists(p));
		}
	}
	
	@Test
	public void deserializeTest() throws FileNotFoundException {
		Object o = FileWriter.deSerializable("test.ser");
		Paper p = (Paper)o;
		
		assertTrue(p.getAuthor().equals("Harmeet Singh"));
	}
	
	@Test
	public void paperSerialTest() throws FileNotFoundException {
		Paper p = new Paper(f3, "Owen Meaker", c);
		Paper q = new Paper(f2, "Owen Meaker", c);
		
		User u = new User("owenmeaker@yahoo.com", 15, c);
		//u.addObserver(new SerializableController());
		u.addPaper(p);
		
		FileWriter.writeSerializable(u, u.getFileSerialName());
		
		
		Object object = FileWriter.deSerializable(u.getFileSerialName());
		User v = (User)object;
		
		System.out.println(v.getPaper());
		System.out.println(v.getPaper().size());
		
		assertNotNull(v);
		assertNotEquals(v.getPaper().size(), 0);
		
		u.addPaper(q);
		
		Object o = FileWriter.deSerializable(u.getFileSerialName());
		User w = (User)o;
		
		System.out.println(w.getPaper());
		System.out.println(w.getPaper().size());
		
		assertNotNull(w);
		assertNotEquals(w.getPaper().size(), 0);
		//assertTrue(w.getPaper().size() > v.getPaper().size());
	}
	
	@Test
	public void observerTest() throws FileNotFoundException {
		u.addObserver(new SerializableController());
		
		u.addPaper(new Paper(f1, "Harmeet Singh", c));
		
		FileWriter.writeSerializable(u, u.getFileSerialName());
		
		User u1 = (User) FileWriter.deSerializable(u.getFileSerialName());
		//User u2 = (User) FileWriter.deSerializable(u.getFileSerialName());
		
		assertEquals(u.getPaper().size(), u1.getPaper().size());

		u.addPaper(new Paper(f1, "Harmeet Singh", c));
		
		User u2 = (User) FileWriter.deSerializable(u.getFileSerialName());

		assertNotEquals(u.getPaper().size(), u1.getPaper().size());
		assertNotEquals(u1.getPaper().size(), u2.getPaper().size());
	}
	
	@Test
	public void loadDatabse() throws FileNotFoundException {
		AccountDatabase  aDB = (AccountDatabase) FileWriter.deSerializable("AccountDatabase.ser");
		assertTrue(aDB.getAccounts().size() == 5);
	}
}
