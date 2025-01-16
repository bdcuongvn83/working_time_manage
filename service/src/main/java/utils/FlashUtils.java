/**
 * 
 */
package utils;

import javax.faces.context.FacesContext;

/**
 * @author cuongbd
 *
 */
public class FlashUtils {
	/**
	 * put to Flash from FacesContext
	 * @param key
	 * @param value
	 */
	public static void putFlash(String key, Object value) {
		
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put(key, value);
	}
	
	/**
	 * get Flash param from FacesContext
	 * @param key
	 * @return
	 */
	public static Object getFlash(String key) {
		
		return FacesContext.getCurrentInstance().getExternalContext().getFlash().get(key);
	}
	
}
