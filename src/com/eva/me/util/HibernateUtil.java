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
	private static final SessionFactory sessionFactory;
	private static final String configPath = "../config/com/eva/me/hibernate/hibernate.cfg.xml";
	
	static {
		SessionFactory temp;
		try {
			Log.i("Session Factory Initialize Begin...");
			temp = new Configuration().configure(configPath).buildSessionFactory();
			Log.i("Session Factory Initialize Success...");
		} catch (Exception e) {
			temp = null;
			Log.e("session factory not initialized");
			e.printStackTrace();
		}
		sessionFactory = temp;
	}
	
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
