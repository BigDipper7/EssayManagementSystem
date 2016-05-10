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
	 * READ
	 * @return
	 */
	public Essay getEssayById(long id);
	
	public void updateEssay();
	
	public void deleteEssay();
}
