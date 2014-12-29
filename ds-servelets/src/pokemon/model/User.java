package pokemon.model;

/**
 * 
 * Class for storing basic information about a user
 *
 */
public class User {

	/**
	 * nickname of the user
	 */
	private String nickname;
	
	/**
	 * password of the user
	 */
	private String password;
	
	/**
	 * Constructor
	 * initializes a user
	 * @param nickname
	 * 					nickname of the user
	 * @param password
	 * 					password of the user
	 */
	public User(String nickname, String password) {
		super();
		this.nickname = nickname;
		this.password = password;
	}

	/**
	 * Getter-Method for the nickname
	 * @return
	 * 			the nickname of the user
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * Setter-Method for the nickname
	 * @param nickname
	 * 			the nickname of the user
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * Getter-Method for the password
	 * @return
	 * 			the password of the user
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter-Method for the password
	 * @param password
	 * 			the password of the user
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}
