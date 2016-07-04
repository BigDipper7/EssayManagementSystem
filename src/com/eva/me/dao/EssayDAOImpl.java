/**
 * 
 */
package com.eva.me.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.ant.FindLeaksTask;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import com.eva.me.funcinterf.Executable;
import com.eva.me.model.Essay;
import com.eva.me.util.HibernateUtil;
import com.eva.me.util.Log;
import com.mysql.cj.fabric.xmlrpc.base.Params;

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
	 * @see com.eva.me.dao.EssayDAO#getEssayListWithLimit(int, int)
	 */
	@Override
	public List<Essay> getEssayListWithLimit(int start, int length) {
//		Map<String, Integer> params = new HashMap<>();
//		params.put("start", start);
//		params.put("length", length);
		return baseProcess(new Executable< List<Essay>, Void >() {

			@Override
			public List<Essay> execute(Session session, Void toExecute) {
				final String hql = "from Essay";
				Query query = session.createQuery(hql);
				query.setFirstResult(start);
				query.setMaxResults(length);
				List<Essay> res = query.list();
				
				return res;
			}
		}, null);
	}

	/* (non-Javadoc)
	 * @see com.eva.me.dao.EssayDAO#getEssayListWithLimit(int, int, java.lang.String)
	 */
	@Override
	public List<Essay> getEssayListWithLimit(int start, int length, String searchTxt) {
//		Map<String, Object> params = new HashMap<>();
//		params.put("start", start);
//		params.put("length", length);
//		params.put("searchTxt", searchTxt);
		
		return baseProcess(new Executable< List<Essay>, Void >() {

			@Override
			public List<Essay> execute(Session session, Void toExecute) {
				Criteria criteria = session.createCriteria(Essay.class);
				criteria.setFirstResult(start);
				criteria.setMaxResults(length);
								
				SimpleExpression likeContent = Restrictions.like("content", searchTxt, MatchMode.ANYWHERE);
				SimpleExpression likeTitle = Restrictions.like("title", searchTxt, MatchMode.ANYWHERE);
				SimpleExpression likeAuthor = Restrictions.like("author", searchTxt, MatchMode.ANYWHERE);
				try {
					int idLike = Integer.parseInt(searchTxt);
					Log.i("==== idlike:"+idLike);

					SimpleExpression likeId = Restrictions.like("id", searchTxt, MatchMode.ANYWHERE);
					Restrictions.disjunction().add(likeId);
					
				} catch (Exception e) {
					Log.w("Search Text["+searchTxt+"] is not need to search in ids");
//					e.printStackTrace();
				}
				criteria.add(
						Restrictions.disjunction().add(likeContent)
							.add(likeTitle)
							.add(likeAuthor));
				
				List<Essay> res = criteria.list();
				Log.d("=================RES====================");
				Log.d(res);
				return res;
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

	/* (non-Javadoc)
	 * @see com.eva.me.dao.EssayDAO#getAllCount()
	 */
	@Override
	public long getAllCount() {
		return baseProcess(new Executable<Long, Void>() {

			@Override
			public Long execute(Session session, Void toExecute) {
				
				final String hql = "select count(*) from Essay";
				long result = (long) session.createQuery(hql).uniqueResult();
				
				return result;
			}
		}, null);
	}

	/* (non-Javadoc)
	 * @see com.eva.me.dao.EssayDAO#getAllCount(java.lang.String)
	 */
	@Override
	public long getAllCount(String searchTxt) {
		
		return baseProcess(new Executable<Long, Void>() {

			@Override
			public Long execute(Session session, Void toExecute) {
				Criteria criteria = session.createCriteria(Essay.class);
				
				SimpleExpression likeContent = Restrictions.like("content", searchTxt, MatchMode.ANYWHERE);
				SimpleExpression likeTitle = Restrictions.like("title", searchTxt, MatchMode.ANYWHERE);
				SimpleExpression likeAuthor = Restrictions.like("author", searchTxt, MatchMode.ANYWHERE);
				try {
					int idLike = Integer.parseInt(searchTxt);
					Log.i("==== idlike:"+idLike);

					SimpleExpression likeId = Restrictions.like("id", searchTxt, MatchMode.ANYWHERE);
					Restrictions.disjunction().add(likeId);
					
				} catch (Exception e) {
					Log.w("Search Text["+searchTxt+"] is not need to search in ids");
//					e.printStackTrace();
				}
				
				criteria.add(
						Restrictions.disjunction().add(likeContent)
							.add(likeTitle)
							.add(likeAuthor));
				criteria.setProjection(Projections.rowCount());
				
				
				long res = (long) criteria.uniqueResult();
				Log.d("=================Filter count====================");
				Log.d(res);
				return res;
			}
		}, null);
	}

}
