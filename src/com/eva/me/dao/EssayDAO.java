/**
 * 
 */
package com.eva.me.dao;

import java.util.List;


import com.eva.me.model.Essay;

/**
 * @author phoen_000
 *
 */
public interface EssayDAO {
	/**
	 * CREATE
	 * @return create essay row id
	 */
	public int addEssay(Essay essay);
	/**
	 * READ
	 * @return
	 */
	public List<Essay> getAllEssayList();

	/**
	 * GET Essay List with limit(start, length)
	 * @param start
	 * @param length
	 * @return
	 */
	public List<Essay> getEssayListWithLimit(final int start, final int length);
	
	/**
	 * GET Essay List with limit(start, length, searchTet)
	 * @param start
	 * @param length
	 * @param searchTxt
	 * @return
	 */
	public List<Essay> getEssayListWithLimit(final int start, final int length, final String searchTxt);
	
	/**
	 * get all essay list count, get the real count with all lists
	 * @return
	 */
	public long getAllCount();
	
	/**
	 * get all essay list count, get the real count with all lists
	 * @return
	 */
	public long getAllCount(final String searchTxt);
	
	/**
	 * READ
	 * @return
	 */
	public Essay getEssayById(int id);
	
	public void updateEssay(Essay essay);
	
	public void deleteEssay(int id);
}
