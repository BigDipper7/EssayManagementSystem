/**
 * 
 */
package com.eva.me.test;

import java.util.List;

import org.junit.Test;

import com.eva.me.dao.EssayDAOImpl;
import com.eva.me.model.Essay;
import com.eva.me.util.ExcelUtil;
import com.eva.me.util.Log;

/**
 * @author ViolinSolo
 *
 */
public class TestExcelUtil {
	
	@Test
	public void testDataExportToXLS() {
		List<Essay> list = new EssayDAOImpl().getAllEssayList();
		
		ExcelUtil.exportDBtoFSXLS(list);
	}
	
	@Test
	public void testDataImportToObjectList() {
		final String filePath = "d:\\test\\2016_07_24__02_55_44_812.xlsx";
		List<Essay> list = ExcelUtil.importFSXLStoObjectList(filePath);
		
		for (Essay essay : list) {
			Log.d(essay);
		}
	}
}
