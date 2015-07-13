package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import model.Paper;
import model.User;

/**
 * Writes Files to the file system.
 * 
 * @author Owen Meaker
 *
 */
public class FileWriter {
	private static String REGEX = "[.][^.]+$"; // replaces the extension of a file. Found at StackOverflow question 924394
	

	/**
	 * Writes a paper to the filesystem.
	 * @param file - the file to upload.
	 * @param user - the user that owns the file.
	 */
	public static void writePaperFile(final File file, final User user) {
		Path oldPath = file.toPath();
		String fileNameNoExt = file.getName().replaceFirst(REGEX, "");
		Path newPath = Paths.get("papers\\" + String.valueOf((user.getConference().getConferenceId())) + "\\" + user.getUserName() + "\\" + fileNameNoExt + "\\" + file.getName());
		System.out.println(newPath.toFile());
		try {
			if (!Files.exists(newPath)) {
				Files.createDirectories(newPath);
				Files.copy(oldPath, newPath, StandardCopyOption.REPLACE_EXISTING);				
			} else {
				//TODO: Implement
				Files.copy(oldPath, newPath, StandardCopyOption.REPLACE_EXISTING);
			}
			//user.addPaper(new Paper(newPath.toFile(), user.getUserName(), user.getConference()));
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Writes a review file to the filesystem.
	 * @param file - the file to upload.
	 * @param user - the user who wrote the review.
	 * @param paper - the paper the review is for.
	 */
	public static void writeReviewFile(final File file, final User user, final Paper paper) {
		Path oldPath = file.toPath();
		String fileNameNoExt = paper.getPaper().getName().replaceFirst(REGEX, "");
		Path newPath = Paths.get("papers\\" + String.valueOf((user.getConference().getConferenceId())) + "\\" + user.getUserName() + "\\" + fileNameNoExt + "\\reviews\\" + file.getName());
		
		try {			
			if (!Files.exists(newPath)) {
				Files.createDirectories(newPath);
				Files.copy(oldPath, newPath, StandardCopyOption.REPLACE_EXISTING);
			} else {
				//TODO: Implement
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Writes a recomendation file to the filesystem.
	 * @param file - the file to upload.
	 * @param user - the user who wrote the recomendation.
	 * @param paper - the paper the review is for.
	 */
	public static void writeRecommendationFile(final File file, final User user, final Paper paper) {
		Path oldPath = file.toPath();
		String fileNameNoExt = paper.getPaper().getName().replaceFirst(REGEX, "");
		Path newPath = Paths.get("papers\\" + String.valueOf((user.getConference().getConferenceId())) + "\\" +  user.getUserName() + "\\" + fileNameNoExt + "\\recomendation\\" + file.getName());
		
		try {	
			if (!Files.exists(newPath)) {
				Files.createDirectories(newPath);
				Files.copy(oldPath, newPath, StandardCopyOption.REPLACE_EXISTING);
			} else {
				//TODO: Implement
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeAcceptanceFile(final File file, final User user, final Paper paper) {
		Path oldPath = file.toPath();
		String fileNameNoExt = paper.getPaper().getName().replaceFirst(REGEX, "");
		Path newPath = Paths.get("papers\\" + String.valueOf((user.getConference().getConferenceId())) + "\\" + user.getUserName() + "\\" + fileNameNoExt + "\\" + file.getName());
		
		try {	
			if (!Files.exists(newPath)) {
				Files.createDirectories(newPath);
				Files.copy(oldPath, newPath, StandardCopyOption.REPLACE_EXISTING);
			} else {
				//TODO: Implement
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Writes a serialized object to the filesystem.
	 * 
	 * @param obj - the object to serialize.
	 * @param name - the name for the file. 
	 * Convention says that serialized objects end in ".ser"
	 */
	public static void writeSerializable(final Object obj, final String name) {
		try {
			Path p = Paths.get("objects\\" + name);
			if (!Files.exists(p)) {
				Files.createDirectories(p);
				Files.delete(p);
				Files.createFile(p);
			}
			
			FileOutputStream fileOut = new FileOutputStream("objects\\" + name);
			
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(obj);
			out.close();
			fileOut.close();
			System.out.println("object " + obj + " serialized");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Reads a serialized file and deserializes it into an object.
	 * 
	 * @param name - the name of the file to read.
	 * 
	 * @return an object. It will need to be cast before usage.
	 * @throws FileNotFoundException 
	 */
	public static Object deSerializable(final String name) throws FileNotFoundException {
		Object obj = null;
		try {
			FileInputStream fileIn = new FileInputStream("objects\\" + name);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			obj = in.readObject();
			in.close();
			fileIn.close();
			//System.out.println("object " + obj + " deserialized");
		} catch(IOException i) {
			i.printStackTrace();
			throw new FileNotFoundException();
		} catch(ClassNotFoundException c) {
			c.printStackTrace();
		}
		return obj;
	}
}
