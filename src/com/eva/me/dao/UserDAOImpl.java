/**
 * 
 */
package com.eva.me.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.engine.spi.ExecutableList;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.eva.me.funcinterf.Executable;
import com.eva.me.model.Essay;
import com.eva.me.model.User;
import com.eva.me.util.HibernateUtil;
import com.eva.me.util.Log;

/**
 * @author phoen_000
 *
 */
public class UserDAOImpl extends BaseDAO implements UserDAO {

	/* (non-Javadoc)
	 * @see com.eva.me.dao.UserDAO#getUserByUserName(java.lang.String)
	 */
	@Override
	public User getUserByUserName(String userName) {
		if (StringUtils.isEmpty(userName)) {
			return null;
		}
		
		return baseProcess(new Executable<User, String>() {

			@Override
			public User execute(Session session, String toExecute) {
				final String hql = "from User where username = :username";
				Query query = session.createQuery(hql);
				query.setString("username", userName);
				return (User) query.uniqueResult();
			}
		}, userName);
	}

	/* (non-Javadoc)
	 * @see com.eva.me.dao.UserDAO#getAllUsers()
	 */
	@Override
	public List<User> getAllUsers() {
		return baseProcess(new Executable<List<User>, Void>() {

			@Override
			public List<User> execute(Session session, Void toExecute) {
				final String sql = "from User";
				List<User> result = session.createQuery(sql).list();
				return result;
			}
		}, null);
	}

	
	
	
	
//	/* (non-Javadoc)
//	 * @see com.eva.me.dao.UserDAO#list()
//	 */
//	@Override
//	@Transactional
//	public List<User> list() {
//		Log.i("get all users...");
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		
//		session.beginTransaction();
//		
//		List<User> resuUsers = null;
//		String sql = "SELECT * FROM test;";
//		resuUsers = (List<User>)session.createQuery(sql).list();
//		
//		session.getTransaction().commit();
//		return resuUsers;
//	}

}
