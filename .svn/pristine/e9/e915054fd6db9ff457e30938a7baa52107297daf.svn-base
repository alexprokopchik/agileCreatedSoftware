package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

import view.WindowBuilderTest;

/**
 * Starting Point of Program.
 * @author Owen Meaker
 *
 */
public class Main {
	/**
	 * Path of database.
	 */
//	public static String FILE_PATH = "test1.txt";
	public static String FILE_PATH = "file.txt";
	
	public static void main(String[] args) throws Exception {
		start();
	}

	/**
	 * Creates the database and starts the gui.
	 */
	private static void start() {
		File file = new File(Paths.get(FILE_PATH).toUri());
		
		DatabaseConstructor dbCon = new DatabaseConstructor();
		
		try {
			dbCon.createDatabases(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		new WindowBuilderTest(dbCon);
	}
}
