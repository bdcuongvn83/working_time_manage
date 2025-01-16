/**
 * 
 */
package utils;

/**
 * @author cuongbd
 *
 */
public class StringUtils {
	
	/**
	 * nullOrblank
	 * @param value
	 * @return
	 */
	public static boolean nullOrblank(Object value) {
		if (value == null || value.toString().trim().length() == 0) {
			return true;
		}
		
		return false;
	}
	
	public static String toEmpty(Object value) {
		
		return value != null ? "" : value.toString();
	}
}
