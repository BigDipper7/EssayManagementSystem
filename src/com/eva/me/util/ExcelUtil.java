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
		HSSFSheet sheet = workbook.createSheet("�ʴ��б�");
		
		//init titles:
		final int titleOffset = 1;
		HSSFRow rowTitle = sheet.createRow(0);
		rowTitle.createCell(0).setCellValue("#ID");
		rowTitle.createCell(1).setCellValue("����");
		rowTitle.createCell(2).setCellValue("��");
		
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
        
        //�ж��Ƿ�Ϊexcel�����ļ�
        if(!filePath.endsWith(".xls")&&!filePath.endsWith(".xlsx"))
        {
            System.out.println("�ļ�����excel����");
        }
        
        FileInputStream fis =null;
        Workbook wookbook = null;
        
        try
        {
            //��ȡһ�����Ե�ַ����
              fis = new FileInputStream(filePath);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
       
        try 
        {
            //2003�汾��excel����.xls��β
            wookbook = new HSSFWorkbook(fis);//�õ�������
             
        } 
        catch (Exception ex) 
        {
            //ex.printStackTrace();
            try
            {
                //2007�汾��excel����.xlsx��β
                
                wookbook = new XSSFWorkbook(fis);//�õ�������
            } catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        //�õ�һ��������
        Sheet sheet = wookbook.getSheetAt(0);
        
        //��ñ�ͷ
        Row rowHead = sheet.getRow(0);
        
        //�жϱ�ͷ�Ƿ���ȷ
        if(rowHead.getPhysicalNumberOfCells() != 3)
        {
            System.out.println("��ͷ����������!");
        }
        
        //������ݵ�������
        int totalRowNum = sheet.getLastRowNum();
        
        //Ҫ�������
        String name = "";
        int latitude = 0;
        
       //�����������
        for(int i = 1 ; i <= totalRowNum ; i++)
        {
            //��õ�i�ж���
            Row row = sheet.getRow(i);
            
            //��û�õ�i�е�0�е� String���Ͷ���
            Cell cell = row.getCell((short)0);
            name = cell.getStringCellValue().toString();
            
            //���һ���������͵�����
            cell = row.getCell((short)1);
            latitude = (int) cell.getNumericCellValue();
            
            System.out.println("���֣�"+name+",��γ�ȣ�"+latitude);
            
        }
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println("====== begin generate excel ========");
		
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("demo sheet 1");
		
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("��������дһд����һ��");
		
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
