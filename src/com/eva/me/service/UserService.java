/**
 * 
 */
package com.eva.me.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.eva.me.dao.UserDAOImpl;
import com.eva.me.model.User;
import com.eva.me.util.HibernateUtil;
import com.eva.me.util.Log;

/**
 * @author phoen_000
 *
 */
public class UserService {
	public List<User> getAllUsers() {
		Log.i("get all users...");
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		
		List<User> resuUsers = null;
		String sql = "SELECT * FROM db_essay_ms.test";
		resuUsers = (List<User>)session.createQuery(sql).list();
		
		session.getTransaction().commit();
		return resuUsers;
	}
	
	public int createUser(User user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		
		int id = -1;
		id = (int) session.save(user);
		Log.i("save user : "+user+" : id="+id);
		
		session.getTransaction().commit();
		return id;
	}
	
	public void updateUser(User user) {
		Log.i("update user... : "+user);
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		
		session.update(user);
		
		session.getTransaction().commit();
	}
	
	public void deleteUser(int id) {
		Log.i("delete user... : "+id);
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		
		User userToDel = session.get(User.class, id);
		session.delete(userToDel);
		
		session.getTransaction().commit();
		
	}
	
	public User loginUser(final User user) {
		String curUsrNam = user.getUsername();
		String curUsePsd = user.getPassword();
		
		User result = new UserDAOImpl().getUserByUserName(curUsrNam);
		return (result==null || !result.getPassword().equals(curUsePsd))?null:result;
	}
}
