/**
 * 
 */
package com.eva.me.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.eva.me.dao.EssayDAOImpl;
import com.eva.me.model.Essay;
import com.eva.me.util.Log;

/**
 * @author phoen_000
 *
 */
public class TestEssayDAOImpl {

	@Test
	public void testGetAllEssays() {
		Log.i("================Begin GetAllEssay==========================");
		List<Essay> list = new EssayDAOImpl().getAllEssayList();
		Log.i("----------------Lists----------------------");
		Log.i(list);
		Log.i("----------------Lists----------------------");
		Log.i("================End GetAllEssay==========================");
	}
	
	@Test
	public void testGetEssay() {
		Log.i("================Begin GetEssay==========================");
		Essay essay = new EssayDAOImpl().getEssayById(1);
		Log.i("----------------Ones----------------------");
		Log.d(essay);
		Log.i("----------------Ones----------------------");
		Log.i("================Begin GetEssay==========================");
	}
	
	public void testDeleteEssay() {
		new EssayDAOImpl().deleteEssay(1);
		Log.d("delete 1");
	}
	
	public void testUpdateEssay() {
		Log.d("update");
	}
}
