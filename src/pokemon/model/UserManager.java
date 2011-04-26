package pokemon.model;

import java.util.LinkedList;


public class UserManager {

	private LinkedList<User> users;
	
	public UserManager() {
		
		users = new LinkedList<User>();
		
	}
	
	public boolean verifyLogin(String nickname, String password) {
		for (User u : users) {
			if (u.getNickname().equals(nickname) && u.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
	
	public void addUser(String nickname, String password) {
		users.add(new User(nickname, password));
	}
	
}
