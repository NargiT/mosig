package pokemon.model;

import java.util.LinkedList;

/**
 * Class for managing the users
 * stores the users in a list
 * and can verify the login of each user
 *
 */
public class UserManager {

	/**
	 * List of users
	 */
	private LinkedList<User> users;
	
	/**
	 * Constructor
	 */
	public UserManager() {
		
		users = new LinkedList<User>();
		
	}
	
	/**
	 * Verifys if login-information is correct
	 * traverses the users in the system to check if name and password are right
	 * @param nickname
	 * 					nickname of the user
	 * @param password
	 * 					password of the user
	 * @return
	 * 					true if login-information is correct, false otherwise
	 */
	public boolean verifyLogin(String nickname, String password) {
		for (User u : users) {
			if (u.getNickname().equals(nickname) && u.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Adds a user to the list of users in the system
	 * @param nickname
	 * 					nickname of the user
	 * @param password
	 * 					password of the user
	 */
	public void addUser(String nickname, String password) {
		users.add(new User(nickname, password));
	}
	
}
