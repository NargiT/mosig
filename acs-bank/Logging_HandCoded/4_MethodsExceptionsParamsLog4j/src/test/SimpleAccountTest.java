package test;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;

import banking.AccountException;
import banking.InterAccountOperations;
import banking.SimpleAccountImpl;

/**
 *  A simple banking account test class.
 *
 *@author     <a href="mailto:Sara.Bouchenak@inria.fr">Sara Bouchenak</a>
 *@version    1.0 - 13/01/2004
 */
public class SimpleAccountTest
{

	/**********************************/
	/* ADDING LOGGIN BY HAND: METHODS */
	/**********************************/
	//static java.util.logging.Logger logger = java.util.logging.Logger.getLogger("trace");
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SimpleAccountTest.class.getName());
	static PatternLayout layout = new PatternLayout("%d{HH:mm:ss} %r [%M] %-5p %c - %m%n");
	static ConsoleAppender stdout = new ConsoleAppender(layout);
	
	public static void main(String[] args)
		throws AccountException
	{
		BasicConfigurator.configure(stdout);
		/*************************************/
		/* ADDING LOGGIN BY HAND: PARAMETERS */
		/*************************************/
		String arguments = "\n\t[This: null]\n\t[Args: ("
			+ args + ")";

		/**********************************/
		/* ADDING LOGGIN BY HAND: METHODS */
		/**********************************/
		//logger.logp(Level.INFO, "SimpleAccountTest", 
			//"main" + arguments, "Entering");
		logger.info("Entering"+arguments);
		/*************************************/
		/* ADDING LOGGIN BY HAND: EXCEPTIONS */
		/*************************************/
		try {

			SimpleAccountImpl 	account1, account2;
			int					accountNb1 = 1, accountNb2 = 2;
		
			account1	= new SimpleAccountImpl(accountNb1, 100);
			account2	= new SimpleAccountImpl(accountNb2, 0);
				
			System.out.println("\nSimpleAccountTest.main: Initially, account1.balance="
				+ account1.getBalance() + ", account2.balance="
				+ account2.getBalance());
				
			InterAccountOperations.transfer(account1, account2, 100);
			System.out.println("\nSimpleAccountTest.main: After 1st transfer 100 ac1 -> ac2, account1.balance="
				+ account1.getBalance() + ", account2.balance="
				+ account2.getBalance());
					
			InterAccountOperations.transfer(account1, account2, 100);
			System.out.println("\nSimpleAccountTest.main: After 2nd transfer 100 ac1 -> ac2, account1.balance="
				+ account1.getBalance() + ", account2.balance="
				+ account2.getBalance());
		
		/*************************************/
		/* ADDING LOGGIN BY HAND: EXCEPTIONS */
		/*************************************/
		} catch (AccountException e) {
//			logger.logp(Level.WARNING, "SimpleAccountTest", 
//				"main", "Exception", e);
			logger.warn("Exception"+arguments,e);
//			throw e;
		}
	}
	
}