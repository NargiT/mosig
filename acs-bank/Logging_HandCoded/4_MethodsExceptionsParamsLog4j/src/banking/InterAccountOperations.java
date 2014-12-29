package banking;

/**********************************/
/* ADDING LOGGIN BY HAND: METHODS */
/**********************************/
import java.util.logging.Logger;
import java.util.logging.Level;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;

import test.SimpleAccountTest;

/**
 *  A class that provides inter-account operations.
 *
 *@author     <a href="mailto:Sara.Bouchenak@inria.fr">Sara Bouchenak</a>
 *@version    1.0 - 13/01/2004
 */
public class InterAccountOperations {

	/**********************************/
	/* ADDING LOGGIN BY HAND: METHODS */
	/**********************************/
	//static Logger logger = Logger.getLogger("trace");
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(InterAccountOperations.class.getName());
	static PatternLayout layout = new PatternLayout("%d{HH:mm:ss} %r [%M] %-5p %c - %m%n");
	static ConsoleAppender stdout = new ConsoleAppender(layout);
	
	/**
	 * Performs funds transfer between two banking accounts.
	 * 
	 * @param	from	The origin account for funds transfer
	 * @param	to		The target account for funds transfer
	 * @param	amount	The amount to transfer
	 * @throws	AccountException	If a problem occurs during
	 * the <code>debit</code> operation, e.g., insufficient balance.
	 */
	public static void transfer(Account from, Account to, 
		float amount)
		throws AccountException
	{
		
		/*************************************/
		/* ADDING LOGGIN BY HAND: PARAMETERS */
		/*************************************/
		String args = "\n\t[This: null]\n\t[Args: ("
			+ from + ", " + to + ", " + amount + ")";

		/**********************************/
		/* ADDING LOGGIN BY HAND: METHODS */
		/**********************************/
//		logger.logp(Level.INFO, "InterAccountOperations", 
//			"transfer" + args, "Entering");
		logger.info("Entering"+args);

		/*************************************/
		/* ADDING LOGGIN BY HAND: EXCEPTIONS */
		/*************************************/
		try {
			
			// Deducts the amount from the 'from' account
			from.debit(amount);
			// Adds the amount to the 'to' account
			to.credit(amount);
		
		/*************************************/
		/* ADDING LOGGIN BY HAND: EXCEPTIONS */
		/*************************************/
		} catch (AccountException e) {
//			logger.logp(Level.WARNING, "InterAccountOperations", 
//				"transfer", "Exception", e);
			logger.warn("Exception"+args,e);
//			throw e;
		}
		
	}
	
}