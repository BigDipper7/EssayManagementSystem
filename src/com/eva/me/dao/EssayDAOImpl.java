/**
 * 
 */
package com.eva.me.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.eva.me.funcinterf.Executable;
import com.eva.me.model.Essay;
import com.eva.me.util.HibernateUtil;

/**
 * @author phoen_000
 *
 */
public class EssayDAOImpl extends BaseDAO implements EssayDAO{

	/* (non-Javadoc)
	 * @see com.eva.me.dao.EssayDAO#addEssay(com.eva.me.model.Essay)
	 */
	@Override
	public int addEssay(Essay essay) {
		/*
		Transaction trans = null;
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			trans = session.beginTransaction();
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
		*/
		
		int result = baseProcess(new Executable<Integer, Essay>() {

			@Override
			public Integer execute(Session session, Essay toExecute) {
				return (Integer) session.save(toExecute);
			}
		}, essay);
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.eva.me.dao.EssayDAO#getAllEssayList()
	 */
	@Override
	public List<Essay> getAllEssayList() {
		
		return baseProcess(new Executable<List<Essay>, Void>() {

			@Override
			public List<Essay> execute(Session session, Void toExecute) {
				final String sql = "from Essay";
				List<Essay> result = session.createQuery(sql).list();
				return result;
			}
		}, null);
	}

	/* (non-Javadoc)
	 * @see com.eva.me.dao.EssayDAO#getEssayById(long)
	 */
	@Override
	public Essay getEssayById(int id) {
		return baseProcess(new Executable<Essay, Integer>() {

			@Override
			public Essay execute(Session session, Integer toExecute) {
				return session.get(Essay.class, id);
			}
		}, id);
	}

	/* (non-Javadoc)
	 * @see com.eva.me.dao.EssayDAO#updateEssay()
	 */
	@Override
	public void updateEssay(Essay essay) {
		baseProcess(new Executable<Void, Essay>() {

			@Override
			public Void execute(Session session, Essay toExecute) {
				session.update(essay);
				return null;
			}
		}, essay);
	}

	/* (non-Javadoc)
	 * @see com.eva.me.dao.EssayDAO#deleteEssay()
	 */
	@Override
	public void deleteEssay(int id) {
		baseProcess(new Executable<Essay, Integer>() {

			@Override
			public Essay execute(Session session, Integer toExecute) {
				Essay essay = session.load(Essay.class, id);
				session.delete(essay);
				return essay;
			}
		}, id);
	}

}