/**
 * 
 */
package utils;

/**
 * @author cuongbd
 *
 */
public class NumberUtils {
	public static Long toLong(Object value) {
		Long result = null;
		try {
			result = Long.parseLong(value.toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
}
