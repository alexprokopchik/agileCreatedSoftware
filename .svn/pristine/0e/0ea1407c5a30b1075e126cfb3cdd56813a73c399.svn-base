package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This represents a recommendation, containing the recommendation and other related information for it.
 * @author Harmeet
 *
 * Contributors: Janelle
 */
public class Recommendation {
	
	private List<String> questions;
	private List<String> answers; //Do we still need this?
	private int recommendation;
	private Map<String, String> questionAnswer;

	/**
	 * Constructor for recommendation class.
	 */
	public Recommendation() {
		questions = new ArrayList<String>();
		answers = new ArrayList<String>();
		questionAnswer = new HashMap<String, String>();
		recommendation = 0;
		addQuestions();
	}
	
	/**
	 * @param theAnswer The answer to the question.
	 */
	public void setAnswers(final String theAnswer) {
		answers.add(theAnswer);
	}
	
	/**
	 * @param theValue The recommendation value.
	 */
	public void setRecommendation(final int theValue) {
		recommendation = theValue;
	}

	/**
	 * @return the questions
	 */
	public List<String> getQuestions() {
		return questions;
	}

	/**
	 * @return the answers
	 */
	public List<String> getAnswers() {
		return answers;
	}

	/**
	 * @return the recommendation
	 */
	public int getRecommendation() {
		return recommendation;
	}
	
	/**
	 * Adds an answer
	 * @param theQuestion - the question answering
	 * @param theAnswer - the answer.
	 */
	public void addAnswer(String theQuestion, String theAnswer) {
		questionAnswer.put(theQuestion, theAnswer);
	}
	
	/**
	 * Adding the questions.
	 */
	private void addQuestions() {
		questions.add("Summary Recommendation");
		questions.add("Rationale for Recommendation");
	}
	

}
