/**
 * 
 */
package com.eva.me.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.eva.me.model.Essay;



/**
 * @author ViolinSolo
 *
 */
public class ExcelUtil {
	/**
	 * export object list to excel in FS,
	 * @param allEssays
	 */
	public static void exportDBtoFSXLS(List<Essay> allEssays) {
//		allEssays = new EssayDAOImpl().getAllEssayList();
		
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
			row.createCell(0).setCellValue(id);
			row.createCell(1).setCellValue(question);
			row.createCell(2).setCellValue(answer);
			
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
	
	/**
	 * import FS excel and transfer it to object list
	 * @param filePath object file path
	 * @return
	 */
	public static List<Essay> importFSXLStoObjectList(String filePath) {
		//String filePath = "E:\\123.xlsx";
        
        //判断是否为excel类型文件
        if(!filePath.endsWith(".xls")&&!filePath.endsWith(".xlsx"))
        {
            System.out.println("文件不是excel类型");
        }
        
        FileInputStream fis =null;
        Workbook wookbook = null;
        
        try
        {
            //获取一个绝对地址的流
              fis = new FileInputStream(filePath);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
       
        try 
        {
            //2003版本的excel，用.xls结尾
            wookbook = new HSSFWorkbook(fis);//得到工作簿
             
        } 
        catch (Exception ex) 
        {
            //ex.printStackTrace();
            try
            {
                //2007版本的excel，用.xlsx结尾
                
                wookbook = new XSSFWorkbook(fis);//得到工作簿
            } catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        //得到一个工作表
        Sheet sheet = wookbook.getSheetAt(0);
        
        //获得表头
        Row rowHead = sheet.getRow(0);
        
        //判断表头是否正确
        if(rowHead.getPhysicalNumberOfCells() != 3)
        {
            System.out.println("表头的数量不对!");
        }
        
        //获得数据的总行数
        int totalRowNum = sheet.getLastRowNum();
        
        //要获得属性
        String name = "";
        int latitude = 0;
        
       //获得所有数据
        for(int i = 1 ; i <= totalRowNum ; i++)
        {
            //获得第i行对象
            Row row = sheet.getRow(i);
            
            //获得获得第i行第0列的 String类型对象
            Cell cell = row.getCell((short)0);
            name = cell.getStringCellValue().toString();
            
            //获得一个数字类型的数据
            cell = row.getCell((short)1);
            latitude = (int) cell.getNumericCellValue();
            
            System.out.println("名字："+name+",经纬度："+latitude);
            
        }
		return null;
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
