/**
 * 
 */
package com.eva.me.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.eva.me.dao.UserDAOImpl;
import com.eva.me.model.User;
import com.eva.me.util.Log;

/**
 * @author phoen_000
 *
 */
public class TestUserDAO {

	@Test
	public void testGetUserByUsername() {
		String userName = "eva";
		User usr = new UserDAOImpl().getUserByUserName(userName);
		
		Log.i("begin print");
		Log.i(usr);
		
	}

}
