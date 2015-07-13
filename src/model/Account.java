package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

/**
 * Represents a single real life person involved with one or more conferences.
 * 
 * @author Janelle, Harmeet
 * 
 * Contributers: Owen, Alex
 */
public class Account extends Observable implements Serializable{
	
	private static final long serialVersionUID = 6165707919342689426L;
	private String firstName;
	private String lastName;
	private String email;
	private int userID;
	private int permissions; 
	private Map<Conference, User> users;
	private Map<Integer, Conference> conferences;
	
	/**
	 * Creates a new Account.
	 */
	public Account() {
		users = new HashMap<Conference, User>();
		conferences = new HashMap<Integer, Conference>();
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
		update();
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
		update();
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
		update();
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(int userID) {
		this.userID = userID;
		update();
	}

	/**
	 * @param permissions the permissions to set
	 */
	public void setPermissions(int permissions) {
		this.permissions = permissions;
		update();
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the userID
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * @return the permissions
	 */
	public int getPermissions() {
		return permissions;
	}
	
	/**
	 * Gets a User associated with this account and conference.
	 * @param theConf - the conference the user is associated with.
	 * @return - A user belonging to the conference.
	 */
	public User getUserConf(final Conference theConf) {
		return users.get(theConf);
	}
	
	/**
	 * Gets a User associated with this account and conference
	 * @param conferenceId - the id for the conference the user is associated with.
	 * @return - A user belonging to the conference.
	 */
	public User getUserConfId(final int conferenceId) {
		
		return users.get(conferences.get(conferenceId));
	}
	
	/**
	 * Adds the user to list of users.
	 * @param theUser - The user to add.
	 */
	public void addUser(final User theUser) {
		users.put(theUser.getConference(), theUser);
		conferences.put(theUser.getConference().getConferenceId(), theUser.getConference());
		update();
	}

	/**
	 * Gets a list of conferences for this account.
	 * @return a List of Conferences.
	 */
	public List<Conference> getConference() {
		Collection<Conference> result = conferences.values();
		ArrayList<Conference> res = new ArrayList<Conference>();
		res.addAll(result);
		return res;
	}
	
	/**
	 * Notifies observers.
	 */
	public void update() {
		setChanged();
		notifyObservers(getFileSerialName());
	}
	
	/**
	 * Gets the name for the serialized version of this file.
	 * @return the serialized file name.
	 */
	public String getFileSerialName() {
		return "A_"+email +".ser";
	}
}
