/**
 * 
 */
package com.eva.me.test;

import java.util.List;

import org.junit.Test;

import com.eva.me.dao.EssayDAOImpl;
import com.eva.me.model.Essay;
import com.eva.me.util.ExcelUtil;

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
}
