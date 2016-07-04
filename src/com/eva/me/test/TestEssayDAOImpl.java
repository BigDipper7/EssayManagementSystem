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
	public void testGetLimitEssays() {
		Log.i("================Begin testGetLimitEssays==========================");
		List<Essay> list = new EssayDAOImpl().getEssayListWithLimit(1, 100);
		Log.i("----------------Lists 1->10----------------------");
		Log.i(list);
		Log.i("----------------Lists----------------------");
		List<Essay> list2 = new EssayDAOImpl().getEssayListWithLimit(1, 3);
		Log.i("----------------Lists 1->3----------------------");
		Log.i(list2);
		Log.i("----------------Lists----------------------");
		List<Essay> list3 = new EssayDAOImpl().getEssayListWithLimit(-1, 3);
		Log.i("----------------Lists -1->3----------------------");
		Log.i(list3);
		Log.i("----------------Lists----------------------");
		List<Essay> list4 = new EssayDAOImpl().getEssayListWithLimit(1, -3);
		Log.i("----------------Lists 1->-3----------------------");
		Log.i(list4);
		Log.i("----------------Lists----------------------");
		Log.i("================End testGetLimitEssays==========================");
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
	
	@Test
	public void testDeleteEssay() {
		new EssayDAOImpl().deleteEssay(8);
		Log.d("delete 1");
	}
	
	@Test
	public void testUpdateEssay() {
		Log.d("update");
		Essay essay = new EssayDAOImpl().getEssayById(1);
		essay.author = "new author2";
		new EssayDAOImpl().updateEssay(essay);
	}
}
