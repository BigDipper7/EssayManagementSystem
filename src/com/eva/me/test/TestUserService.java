/**
 * 
 */
package com.eva.me.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.eva.me.model.User;
import com.eva.me.service.UserService;
import com.eva.me.util.Log;

/**
 * @author phoen_000
 *
 */
public class TestUserService {

	@Test
	public void testGetAll() {
		UserService service = new UserService();
		List<User> users = service.getAllUsers();
		for (User user : users) {
			Log.i("[Test Get All]: "+user);
		}
	}
	
	@Test
	public void testInsertUsers() {
		UserService service = new UserService();
		
		User user1 = new User();
		user1.setPassword("user1 password");
		user1.setUsername("user1 username");
		User user2 = new User();
		user1.setPassword("user2 password");
		user1.setUsername("user2 username");
		User user3 = new User();
		user1.setPassword("user3 password");
		user1.setUsername("user3 username");
		User user4 = new User();
		user1.setPassword("user4 password");
		user1.setUsername("user4 username");
		
		service.createUser(user1);
		service.createUser(user2);
		service.createUser(user3);
		service.createUser(user4);
				
	}

	@Test
	public void testUpdateUsers() {
		UserService service = new UserService();

		User user1 = new User();
		user1.setPassword("user1 password update");
		user1.setUsername("user1 username update");
		
		service.updateUser(user1);
	}

	@Test
	public void testDeleteUser() {
		UserService service = new UserService();
		
		service.deleteUser(2);
	}
	
}
