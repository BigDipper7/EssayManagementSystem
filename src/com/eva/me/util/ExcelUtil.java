/**
 * 
 */
package com.eva.me.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.result.Output;


/**
 * @author ViolinSolo
 *
 */
public class ExcelUtil {
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
