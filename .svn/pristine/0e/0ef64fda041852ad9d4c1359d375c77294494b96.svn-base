package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * Stores a collection of Accounts.
 * @author Harmeet
 * 
 * Contributors: Owen, Janelle, Alex.
 *
 */
public class AccountDatabase extends Observable implements Serializable {
	private static final long serialVersionUID = -5469698452977548392L;
	private Map<String, Account> accounts;
	
	/**
	 * Creates a new database.
	 */
	public AccountDatabase() {
		accounts = new HashMap<String, Account>();
	}
	
	/**
	 * Adds the account to the database.
	 * @param theAccount - the account to add.
	 */
	public void add(final Account theAccount) {
		accounts.put(theAccount.getEmail(), theAccount);
		update();
	}
	
	/**
	 * Gets the map of accounts
	 * @return a map of usernames to accounts.
	 */
	public Map<String, Account> getAccounts() {
		return accounts;
	}
	
	/**
	 * Gets a user from the database.
	 * @param acnt - the account the user is associated with.
	 * @param conferenceId - the id of the conference the user is associated with.
	 * @return a User.
	 */
	public User getUser(final Account acnt, final int conferenceId) {
		return acnt.getUserConfId(conferenceId);
	}
	
	/**
	 * determines whether an account exists with the given email.
	 * @param email - the email to check
	 * @return true or false.
	 */
	public boolean containsAccount(final String email) {
		return accounts.containsKey(email);
	}
	
	/**
	 * Notifies observers.
	 */
	public void update() {
		setChanged();
		notifyObservers("AccountDatabase.ser");
	}
}
