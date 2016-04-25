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
		logger.error("this is error");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("this is a info message");
		logger.fatal("this is a fatal message");
	}
}
