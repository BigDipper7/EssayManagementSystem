/**
 * 
 */
package com.eva.me.test;

import org.apache.log4j.Logger;

/**
 * @author phoen_000
 *
 */
public class TestLog4j {
	private static final String TAG = TestLog4j.class.getName();
	private static Logger logger = Logger.getLogger(TAG);
	
	public static void main(String[] args) {
		logger.debug("this is a debug message");
	}
}
