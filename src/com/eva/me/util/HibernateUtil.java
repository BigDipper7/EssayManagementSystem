/**
 * 
 */
package com.eva.me.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author phoen_000
 *
 */
public class HibernateUtil {
	private static final SessionFactory SESSION_FACTORY;
	
	static {
		try {
			SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
		} catch (Exception e) {
			Log.e("session factory not initialized");
			e.printStackTrace();
		}
		
	}
	
	
	public static SessionFactory getSessionFactory() {
		return SESSION_FACTORY;
	}
}
