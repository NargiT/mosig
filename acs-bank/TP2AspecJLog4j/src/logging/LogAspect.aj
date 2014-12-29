package logging;


import org.apache.log4j.*;
import org.apache.log4j.BasicConfigurator;


/**
 * A logging aspect for the banking application.
 * 
 * @author <a href="mailto:Sara.Bouchenak@inria.fr">Sara Bouchenak</a>
 * @version 1.0 - 13/01/2004
 */
public aspect LogAspect {

	static Logger logger = Logger.getLogger(LogAspect.class);

	/**
	 * Aspect matching each method before their execution
	 */

	pointcut allMeth():  execution(* *.*(..));

	before(): execution(* *.main(..)){
		BasicConfigurator.configure();
	}
	
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
		logger.info("INFO: Entering " + className+" "+methodName + arguments);
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
		logger.info("INFO: Entering " + className+" "+methodName + arguments);
	}

	after() throwing(Throwable t):allMeth()
	{
		String className = thisJoinPointStaticPart.getSignature()
		.getDeclaringTypeName();
		String methodName = thisJoinPointStaticPart.getSignature().getName();
		logger.warn("WARNING: Exception " + className+" "+methodName + "Exception" + t);
	}
}