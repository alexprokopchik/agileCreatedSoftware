package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.*;

import javax.swing.*;

import model.Review;

@SuppressWarnings("serial")
/**
 * This is a visual representation of a review. It displays the question and a radio button or textbox
 * for an answer. 
 * @author Janelle Tin
 *
 */
public class ReviewPanel extends JPanel {
	private Review review;
	private List<String> questions;
	private List<ButtonGroup> buttonGroups;
	private JTextArea answerText;
	//private JPanel questionPanel;
	/**
	 * Create the panel/ constructor for the Review Panel.
	 */
	public ReviewPanel(Review theReview) {
		super();
		setLayout(new GridLayout(0,2,5,5));
		review = theReview;
		questions = new ArrayList<String>();
		buttonGroups = new ArrayList<ButtonGroup>();
		//questionPanel = new JPanel();
		initialize();
	}
	
	/**
	 * The visuals and initializations of the Review Panel including the radio buttons, the text area, and the 
	 * questions that are needed to perform the review.
	 */
	private void initialize() {
		questions = review.getQuestions();
		// loops through questions
		for (String s : questions){
			// Question text
			if(!s.equals("Summary Comments")) {
				JTextArea txtQ = new JTextArea(s);
				txtQ.setEditable(false);
				txtQ.setLineWrap(true);
				txtQ.setWrapStyleWord(true);
				txtQ.setBackground(getBackground());
				JScrollPane scroll = new JScrollPane(txtQ);
				scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
				
				add(scroll);


				// creating buttons
				ButtonGroup bGroup = new ButtonGroup();
				JPanel buttonPanel = new JPanel(new GridLayout(1,0));
				for(int i = 1; i <= 5; i++) {
					JRadioButton b = new JRadioButton(String.valueOf(i));
					b.setActionCommand(String.valueOf(i));
					b.setToolTipText(String.valueOf(i));
					bGroup.add(b);
					buttonPanel.add(b);
					buttonPanel.setBackground(Color.BLUE);
				}
				buttonGroups.add(bGroup);

				add(buttonPanel);
			} else {
				JTextArea txtQ = new JTextArea(s);
				txtQ.setEditable(false);
				txtQ.setLineWrap(true);
				txtQ.setWrapStyleWord(true);
				txtQ.setBackground(getBackground());
				JScrollPane scroll = new JScrollPane(txtQ);
				scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
				
				add(scroll);

				answerText = new JTextArea("TEST \n\n\n TEST");
				add(answerText);
			}
			//buttonPanel.removeAll();
		}
	}
	
	/**
	 * Getter for the review
	 * @return the review
	 */
	public Review getReview() {
		return review;
	}
	
	/**
	 * Getter for the required Questions.
	 * @return the questions
	 */
	public List<String> getQuestions() {
		return questions;
	}
	
	/**
	 * Getter for answers from the ButtonGroups
	 * @return the button Groups
	 */
	public List<ButtonGroup> getButtonGroups() {
		return buttonGroups;
	}

	/**
	 * Getter for the answers from the textArea
	 * @return
	 */
	public JTextArea getAnswerText(){
		return answerText;
	}
}
