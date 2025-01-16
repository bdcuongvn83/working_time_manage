/**
 * 
 */
package net.jsf;

import java.util.Objects;

/**
 * @author cuongbd
 *
 */
public class ExceptionUtil {
	public static Throwable getRootCause(Throwable throwable) {
	    Objects.requireNonNull(throwable);
	    Throwable rootCause = throwable;
	    while (rootCause.getCause() != null && rootCause.getCause() != rootCause) {
	        rootCause = rootCause.getCause();
	    }
	    return rootCause;
	}
}
