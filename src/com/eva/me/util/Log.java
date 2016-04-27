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
	
	public static void d(final Object msg) {
		logger.debug(msg);
	}
	
	public static void e(final Object msg) {
		logger.error(msg);
	}
	
	public static void i(final Object msg) {
		logger.info(msg);
	}
	
	public static void f(final Object msg) {
		logger.fatal(msg);
	}

	public static void w(final Object msg) {
		logger.warn(msg);
	}
}
