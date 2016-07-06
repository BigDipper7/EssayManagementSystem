/**
 * 
 */
package com.eva.me.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author violi
 *
 */
public class TimeUtil {

	/**
	 * get cur format string of cur date
	 * 
	 * @return "yyyy_MM_dd__hh_mm_ss_SSS"
	 */
	public static String getCurrTimStr() {
		Date today;
		String output;
		SimpleDateFormat formatter;
		String pattern = "yyyy_MM_dd__hh_mm_ss_SSS";

		formatter = new SimpleDateFormat(pattern);
		today = new Date();
		output = formatter.format(today);
//		System.out.println(pattern + " " + output);
		return output;
	}
}
