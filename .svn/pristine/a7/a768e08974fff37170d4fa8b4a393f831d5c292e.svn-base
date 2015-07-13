package model;

import controller.DatabaseConstructor;

/**
 * Manages a session: a instance from the time a user logs in until they logout.
 * @author Owen
 * 
 * Contributors: Harmeet.
 *
 */
public class Session {
	private DatabaseConstructor dbCon;
	
	/**
	 * Creates a new session object.
	 * @param theDbcon
	 */
	public Session(final DatabaseConstructor theDbcon) {
		dbCon = theDbcon;
	}
	
	/**
	 * determines if it is a valid email.
	 * @param theEmail - the email to validate
	 * @return if it is valid.
	 */
	public boolean ifValidemail(final String theEmail) {
		return dbCon.actDatabase.containsAccount(theEmail);
	}
}
