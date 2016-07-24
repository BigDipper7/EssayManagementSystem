/**
 * 
 */
package com.eva.me.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.taglibs.standard.lang.jstl.NullLiteral;
import org.hibernate.result.Output;

import com.eva.me.dao.EssayDAOImpl;
import com.eva.me.model.Essay;
import com.sun.org.apache.regexp.internal.recompile;



/**
 * @author ViolinSolo
 *
 */
public class ExcelUtil {
	
	public static void exportDBtoFSXLS(List<Essay> allEssays) {
		allEssays = new EssayDAOImpl().getAllEssayList();
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("问答列表");
		
		//init titles:
		final int titleOffset = 1;
		HSSFRow rowTitle = sheet.createRow(0);
		rowTitle.createCell(0).setCellValue("#ID");
		rowTitle.createCell(1).setCellValue("问题");
		rowTitle.createCell(2).setCellValue("答案");
		
		int i = 0;
		for (Essay essay : allEssays) {
			//for loop to get each essay
			int id = essay.getId();
			String question = essay.getTitle();
			String answer = essay.getContent();
			
			//pre-process of String question and answer
			System.out.println(String.format("[%d] id:%d \nquestion:%s \nanswer:%s", i, id, question, answer));
			
			//new row, init cell
			HSSFRow row = sheet.createRow(i + titleOffset);
			row.getCell(0).setCellValue(id);
			row.getCell(1).setCellValue(question);
			row.getCell(2).setCellValue(answer);
			
			System.out.println("finish row:"+i);
			//index to count:
			i++;
		}
		
		
		// write all memory xls data to 
		FileOutputStream fos = null;
		try {
			
			fos = FileUtil.getXLSFOS();
			
			workbook.write(fos);
			
			fos.flush();
			
		} catch (IOException e) {
			System.err.println("create file error in XLS init stage...");
			e.printStackTrace();
		}finally {
			try {
				if (fos!=null) {
					fos.close();
				}
			} catch (IOException e) {
				System.err.println("FOS close error in XLS init stage...");
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println("====== begin generate excel ========");
		
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("demo sheet 1");
		
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("这就是随便写一写，试一试");
		
		HSSFRow row2 = sheet.createRow(20);
		HSSFCell cell2 = row2.createCell(12);
		cell2.setCellValue("another");
		
		System.out.println("content generate ////");
		
		FileOutputStream fos = null;
		try {
			File f = new File("d:\\wook.xls");
			if (!f.exists()) {
				f.createNewFile();
			}
			fos = new FileOutputStream(f);
			wb.write(fos);
			
			System.out.println("========= all write done =========");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fos!=null) {
				
				try {
					fos.flush();
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
	}
}
