package banking;

/**
 * A general exception thrown when  a problem occurs during 
 * an <code>Account</code> operation.
 *
 *@author     <a href="mailto:Sara.Bouchenak@inria.fr">Sara Bouchenak</a>
 *@version    1.0 - 13/01/2004
 */
public class AccountException
	extends Exception {
	
	/**
	 * Constructs an <code>AccountException</code> with 
	 * no detail message.
	 */
	public AccountException()
	{
		super();
	}

	/**
	 * Constructs an <code>AccountException</code> with 
	 * the specified detail message.
	 * 
	 * @param	message 	The detail message
	 */
	public AccountException(String message)
	{
		super(message);
	}
}