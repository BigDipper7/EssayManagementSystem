/**
 * 
 */
package com.eva.me.service;

import org.hibernate.Session;

import com.eva.me.model.Essay;
import com.eva.me.model.User;
import com.eva.me.util.HibernateUtil;
import com.eva.me.util.Log;

/**
 * @author phoen_000
 *
 */
public class EssayService {
	
	public int createEssay(Essay essay) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		
		int id = -1;
		id = (int) session.save(essay);
		Log.i("save essay : "+essay+" : id="+id);
		
		session.getTransaction().commit();
		return id;
	}

}
