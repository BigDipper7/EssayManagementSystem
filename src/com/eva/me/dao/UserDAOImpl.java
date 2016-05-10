/**
 * 
 */
package com.eva.me.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import com.eva.me.model.User;
import com.eva.me.util.HibernateUtil;
import com.eva.me.util.Log;

/**
 * @author phoen_000
 *
 */
public class UserDAOImpl implements UserDAO {

	/* (non-Javadoc)
	 * @see com.eva.me.dao.UserDAO#list()
	 */
	@Override
	@Transactional
	public List<User> list() {
		Log.i("get all users...");
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		
		List<User> resuUsers = null;
		String sql = "SELECT * FROM test;";
		resuUsers = (List<User>)session.createQuery(sql).list();
		
		session.getTransaction().commit();
		return resuUsers;
	}

}
