package logging;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A logging aspect for the banking application.
 * 
 * @author <a href="mailto:Sara.Bouchenak@inria.fr">Sara Bouchenak</a>
 * @version 1.0 - 13/01/2004
 */
public aspect LogAspect {

	static Logger logger = Logger.getLogger("trace");

	/**
	 * Aspect matching each method before their execution
	 */

	pointcut allMeth():  execution(* *.*(..));

	/*void around(): execution(* *.main(..)){
		try {
			proceed();
		} catch (Exception e) {
			System.out.println("LOL!!!");
		}
	}*/
	
	before(): allMeth() && !target(Object){
		String arguments = "";
		String className = thisJoinPointStaticPart.getSignature()
				.getDeclaringTypeName();
		String methodName = thisJoinPointStaticPart.getSignature().getName();

		arguments += "\n\t[This: null]";
		arguments += "\n\t[Args: (";
		// Getting method argumentss
		Object[] args = thisJoinPoint.getArgs();
		for (int count = 0; count < args.length; count++) {
			arguments += args[count];
		}
		arguments += ")]\n";
		logger.logp(Level.INFO, className, methodName + arguments, "Entering");
	}

	before( Object t ): allMeth() && this(t){
		String arguments = "";
		String className = thisJoinPointStaticPart.getSignature()
				.getDeclaringTypeName();
		String methodName = thisJoinPointStaticPart.getSignature().getName();

		arguments += "\n\t[This:"+ t+"]";
		arguments += "\n\t[Args: (";
		// Getting method argumentss
		Object[] args = thisJoinPoint.getArgs();
		for (int count = 0; count < args.length; count++) {
			arguments += args[count];
		}
		arguments += ")]\n";
		logger.logp(Level.INFO, className, methodName + arguments, "Entering");
	}

	after() throwing(Throwable t):allMeth()
	{
		String className = thisJoinPointStaticPart.getSignature()
		.getDeclaringTypeName();
		String methodName = thisJoinPointStaticPart.getSignature().getName();
		logger.logp(Level.WARNING, className, methodName,"Exception",t);
	}
}