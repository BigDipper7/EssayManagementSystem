/**
 * 
 */
package com.eva.me.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.eva.me.util.Log;

/**
 * @author phoen_000
 *
 */
public class TestLog {

	@Test
	public void testLog() {
//		fail("Not yet implemented");
		Log.d("debug");
		Log.e("error");
		Log.f("fatal");
		Log.i("info");
		Log.w("warn");
	}

}
