/**
 * 
 */
package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author cuongbd
 *
 */
public class DateUtils {
	
	public static final String YYYY_MM_DD_HHMMSS = "yyyy-mm-dd hh:mm:ss";

	/**
	 * formatDate Date to String
	 * @param value
	 * @param pattern 
	 * @return
	 */
	public static String formatDate(Date value, String pattern) {
		if (value == null) {
			return "";
		}
		Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat(pattern);  
        String strDate = dateFormat.format(date);  
		
		return strDate;
	}
	public static Date nextDate(Date value) {
		if (value == null) {
			return new Date();
		}
		//Date date = Calendar.getInstance().getTime();  
		
		 // Sử dụng Calendar để thêm 1 ngày
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(value);
        calendar.add(Calendar.DAY_OF_MONTH, 1);

        // Lấy ngày mới (Date + 1)
        Date nextDay = calendar.getTime();

        // In ra kết quả
        System.out.println("Ngày hôm nay: " + nextDay);
        
        
		return nextDay;
	}
	
	public static Date prevDate(Date value) {
		if (value == null) {
			return new Date();
		}
		//Date date = Calendar.getInstance().getTime();  
		
		 // Sử dụng Calendar để thêm 1 ngày
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(value);
        calendar.add(Calendar.DAY_OF_MONTH, -1);

        // Lấy ngày mới (Date + 1)
        Date prevDate = calendar.getTime();

        // In ra kết quả
        System.out.println("Ngày hôm nay: " + prevDate);
        
        
		return prevDate;
	}
	
}
