/**
 * 
 */
package com.eva.me.util;

import org.apache.log4j.Logger;

/**
 * @author phoen_000
 *
 */
public class Log {
	private static final String TAG = Log.class.getName();
	private static Logger logger = Logger.getLogger(TAG);
	
	public static void d(final String msg) {
		logger.debug(msg);
	}
	
	public static void e(final String msg) {
		logger.error(msg);
	}
	
	public static void i(final String msg) {
		logger.info(msg);
	}
	
	public static void f(final String msg) {
		logger.fatal(msg);
	}

	public static void w(final String msg) {
		logger.warn(msg);
	}
}
