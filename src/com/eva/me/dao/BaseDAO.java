/**
 * 
 */
package com.eva.me.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.eva.me.funcinterf.Executable;
import com.eva.me.util.HibernateUtil;

/**
 * @author phoen_000
 *
 */
public class BaseDAO {
	protected <T, F> T baseProcess(Executable<T, F> executable, F toExecute) {
		Transaction trans = null;
		Session session = null;
		T result = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
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
