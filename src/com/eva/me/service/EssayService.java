/**
 * 
 */
package com.eva.me.service;

import org.hibernate.Session;

import com.eva.me.dao.EssayDAOImpl;
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
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		
//		session.beginTransaction();
		
		int id = -1;
//		id = (int) session.save(essay);
		id = new EssayDAOImpl().addEssay(essay);
		Log.i("save essay : "+essay+" : id="+id);
		
//		session.getTransaction().commit();
		return id;
	}
	
	public void updateEssay(Essay essay) {
		EssayDAOImpl daoImpl = new EssayDAOImpl();
		Log.e("===========Origin essay\n"+essay+"\n\n");
		Essay essayOrigin = daoImpl.getEssayById(essay.getId());
		Log.e("===========New essay\n"+essayOrigin+"\n\n");
		
		essayOrigin.setAuthor(essay.getAuthor());
		essayOrigin.setContent(essay.getContent());
		essayOrigin.setTitle(essay.getTitle());
		
		daoImpl.updateEssay(essayOrigin);
	}
}
