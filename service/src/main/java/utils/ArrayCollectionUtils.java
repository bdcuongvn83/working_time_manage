/**
 * 
 */
package utils;

import java.util.Collection;


/**
 * @author cuongbd
 *
 */
public class ArrayCollectionUtils {
	
	/**
     * Return stream of collection. <br>
     * Workaround for 29581: https://bugs.eclipse.org/bugs/show_bug.cgi?id=433075 <br>
     * @param c The collection
     * @return The stream of collection
     */
//    public static <T> Stream<T> stream(Collection<T> c) {
//        if (isCollectionNullOrEmpty(c)) {
//            return new ArrayList<T>().stream();
//        }
//        
//        return c instanceof IndirectList ? new ArrayList<T>(c).stream() : c.stream();
//    }
    
    /**
     * Check collection is null or empty
     * 
     * @param collection Collection
     * @return true is collection is null or empty
     *         otherwise, return false
     */
    public static boolean isCollectionNullOrEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
}
