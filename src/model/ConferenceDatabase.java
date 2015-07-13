package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * Represents a collection of Conferences.
 * @author Owen
 * 
 * Contributors: Janelle
 *
 */
public class ConferenceDatabase extends Observable implements Serializable{
	private static final long serialVersionUID = 3415097966344515592L;
	private Map<Integer, Conference> conferences;
	
	/**
	 * Creates a new Database.
	 */
	public ConferenceDatabase() {
		conferences = new HashMap<Integer, Conference>();
	}
	
	/**
	 * Adds a Conference.
	 * @param theConference - the conference to add.
	 */
	public void add(Conference theConference) {
		conferences.put(theConference.getConferenceId(), theConference);
		update();
	}
	
	/**
	 * Gets a map of conferences.
	 * @return a map from conference id to conferences.
	 */
	public Map<Integer, Conference> getConferences() {
		return conferences;
	}
	
	/**
	 * Gets a user.
	 * @param acnt - the account of the user.
	 * @param conferenceId - the conference id of the user.
	 * @return a User.
	 */
	public User getUser(Account acnt, int conferenceId) {
		return acnt.getUserConfId(conferenceId);
	}
	
	/**
	 * Whether or not the Database contains the conference.
	 * @param id - the conference id to check
	 * @return true or false.
	 */
	public boolean containsConference(Integer id) {
		return conferences.containsKey(id);
	}
	
	/**
	 * Notifies observers.
	 */
	public void update() {
		setChanged();
		notifyObservers("ConferenceDatabase.ser");
	}
}
