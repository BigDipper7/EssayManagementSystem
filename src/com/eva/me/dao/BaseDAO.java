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
	protected <R, T> R baseProcess(Executable<R, T> executable,final T toExecute) {
		Transaction trans = null;
		Session session = null;
		R result = null;
		
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
