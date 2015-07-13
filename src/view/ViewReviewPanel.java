package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import model.Paper;
import model.Review;
import model.User;

/**
 * Where the user is able to see all of the reviews when Reviews are submitted.
 * 
 * @author Janelle
 *
 */
public class ViewReviewPanel extends JScrollPane {
	private static String REGEX = "[.][^.]+$"; // replaces the extension of a file. Found at StackOverflow question 924394
	private JPanel myPanel;
	private Paper paper;
	private User user; 

	/**
	 * the constructor of the viewReviewPanel. Initializes all of the visuals for the panel.
	 * 
	 * @param u the User
	 * @param p the Paper
	 */
	public ViewReviewPanel(User u, Paper p) {
		super();
		myPanel = new JPanel();
		paper = p;
		user = u;
		List<User> reviewers = user.getConference().getPaperDB().get(p.getPaper()).getReviewers();
		List<Review> reviews = paper.getReviews();
		System.out.println("Reviewers " + reviewers.size());
		System.out.println("Reviews " + reviews.size());
		String fileNameNoExt = paper.getPaper().getName().replaceFirst(REGEX, "");
		File path = new File("papers\\" + String.valueOf((user.getConference().getConferenceId())) + "\\" + paper.getAuthor() + "\\" + fileNameNoExt + "\\reviews\\");
		File[] reviews1 = path.listFiles();
		//File r = new File("papers\\1\\asdf\\9.94r\\reviews\\review_asdf.txt"); // for test
		//File r = new File("papers\\" + String.valueOf((user.getConference().getConferenceId())) + "\\" + paper.getAuthor() + "\\" + fileNameNoExt + "\\reviews\\review_" + user.getUserName() + ".txt"); 
		JTextPane text = new JTextPane();
		text.setEditable(false);
		StringBuilder str = new StringBuilder();
		for(File r : reviews1) {
			try {
				Scanner scanner = new Scanner(r);
				while(scanner.hasNext()) {

					str.append(scanner.nextLine() + "\n");
				}
				//text.setText(str.toString());
				//myPanel.add(text);
				//this.add(myPanel);
				System.out.println(str);
				scanner.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			str.append("END OF REVIEW\n=====\n");
		}
		text.setText(str.toString());
//		File rec = new File("papers\\1\\janelletin@yahoo.com\\9.94r\\recomendation\\recommendation.txt");
//		try {
//			Scanner scanner = new Scanner(rec);
//			while(scanner.hasNext()) {
//
//				str.append(scanner.nextLine() + "\n");
//			}
//			//JTextPane text = new JTextPane();
//			text.setText(str.toString());
//			myPanel.add(text);
//			//this.add(myPanel);
//			//System.out.println(str);
//			scanner.close();
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		myPanel.add(text);
		this.setViewportView(myPanel);
	}
}
