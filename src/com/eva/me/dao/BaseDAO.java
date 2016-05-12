/**
 * 
 */
package com.eva.me.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.eva.me.funcinterf.Executable;
import com.eva.me.util.HibernateUtil;

/**
 * @author phoen_000
 *
 */
public class BaseDAO {
	protected <R, T> R baseProcess(Executable<R, T> executable,final T toExecute) {
		Transaction trans = null;
		Session session = null;
		SessionFactory sessionFactory = null;
		R result = null;
		
		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			try {
				session = sessionFactory.getCurrentSession();
			} catch (HibernateException ex) {
				session = sessionFactory.openSession();
			}
			
			trans = session.beginTransaction();
			
			//executable blocks
			result = executable.execute(session, toExecute);
			//end blocks
			
			session.getTransaction().commit();
		} catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.flush();
				session.close();
			}
		}
		
		return result;
	}
}
