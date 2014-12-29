package banking;

/**
 *  Thrown when an <code>Account</code> balance is insufficient.
 *
 *@author     <a href="mailto:Sara.Bouchenak@inria.fr">Sara Bouchenak</a>
 *@version    1.0 - 13/01/2004
 */
public class InsufficientBalanceException
	extends AccountException {
	
	/**
	 * Constructs an <code>InsufficientBalanceException</code> with 
	 * no detail message.
	 */
	public InsufficientBalanceException()
	{
		super();
	}

	/**
	 * Constructs an <code>InsufficientBalanceException</code> with 
	 * the specified detail message.
	 * 
	 * @param	message 	The detail message
	 */
	public InsufficientBalanceException(String message)
	{
		super(message);
	}
}