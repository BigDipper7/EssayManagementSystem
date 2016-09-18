/**
 * 
 */
package com.eva.me.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.eva.me.dao.EssayDAOImpl;
import com.eva.me.lucene.LuceneIndex;
import com.eva.me.model.Essay;
import com.eva.me.util.ExcelUtil;
import com.eva.me.util.FileUtil;
import com.eva.me.util.Log;
import com.eva.me.util.TimeUtil;

/**
 * @author ViolinSolo
 *
 */
@Controller
public class FileUploadController {

	@RequestMapping(value="/dataImEx", method=RequestMethod.GET)
	public String getDataImportExportPage() {
		return "DataImEx";
	}
	
	
	@RequestMapping(value="/dataExportExcel", method=RequestMethod.POST)
	public String exportXLStoFS(ModelMap modelMap) {
		List<String> infoMsgs = new ArrayList<>();
		
		Log.i("Begin To Get Essays!");
		List<Essay> allEssays = new EssayDAOImpl().getAllEssayList();
		
		Log.i("Get Essays Succeed! Begin To Export");
		ExcelUtil.exportDBtoFSXLS(allEssays);
		
		Log.i("Export Success!");
    	infoMsgs.add("导出成功！");
		
    	
    	modelMap.addAttribute("Infos", infoMsgs);
		return "DataImEx";
	}
	
	@RequestMapping(value="/dataExportExcelDownload", method=RequestMethod.POST)
	public String exportXLStoDownload(ModelMap modelMap, HttpServletResponse response) {
		List<String> infoMsgs = new ArrayList<>();
		
		Log.i("Begin To Get Essays!");
		List<Essay> allEssays = new EssayDAOImpl().getAllEssayList();
		
		Log.i("Get Workbook Ready!");
		//Prepare workbook.....
		HSSFWorkbook wb = ExcelUtil.exportDBtoWorkbook(allEssays);
		
		
		Log.i("Begin download process");
		ByteArrayOutputStream os = new ByteArrayOutputStream();

        try {
            wb.write(os);
        } catch (IOException e) {
        	Log.e("Workbook write to ByteArrayOutputStream ERROR!!!");
            e.printStackTrace();
        }

        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);

        // ����response���������Դ�����ҳ��
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + ("export_" + TimeUtil.getCurrTimStr() + ".xls"));

        ServletOutputStream out = null;
		try {
			out = response.getOutputStream();
		} catch (IOException e1) {
			Log.e("response.getOutputStream(); error!");
			e1.printStackTrace();
		}

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {

            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);

            byte[] buff = new byte[2048];
            int bytesRead;

            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }

        } catch (final IOException e) {
        	Log.e("Write Input to Output Error!");
            e.printStackTrace();
        } finally {
            if (bis != null)
				try {
					bis.close();
				} catch (IOException e) {
					Log.e("BufferedInputStream close error!");
					e.printStackTrace();
				}
            if (bos != null)
				try {
					bos.close();
				} catch (IOException e) {
					Log.e("BufferedOutputStream close error!");
					e.printStackTrace();
				}
        }
		
		Log.i("Export Success!");
    	infoMsgs.add("导出成功！");
		
    	
    	modelMap.addAttribute("Infos", infoMsgs);
		return "DataImEx";
	}
	
    @RequestMapping(value="/singleSaveExcel", method=RequestMethod.POST )
    public String singleSaveOnlyExcel(@RequestParam("file") MultipartFile file, @RequestParam("desc") String desc, ModelMap modelMap ){
		List<String> infoMsgs = new ArrayList<>();
    	
    	System.out.println("File Description:"+desc);
    	String fileName = null;
    	if (!file.isEmpty()) {
            try {
                fileName = file.getOriginalFilename();
                if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")) {
                	Log.e("Upload File Invalid, file type not support, Excel Only!");
                	infoMsgs.add("上传的文件不是Excel类型，请重试");
            		modelMap.addAttribute("Infos", infoMsgs);
					return "DataImEx";
				}
                
                final String filePath = filePathPrefix + File.separator + fileName;
                byte[] bytes = file.getBytes();
                BufferedOutputStream buffStream = 
                        new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                buffStream.write(bytes);
                buffStream.close();
                
                Log.i("You have successfully uploaded " + fileName);
                infoMsgs.add("已成功上传文件：" + fileName + "，请等待数据导入");
        		modelMap.addAttribute("Infos", infoMsgs);
        		
        		//after download success, we can do excel load:
        		new Thread(new Runnable() {
					
					@Override
					public void run() {
						Log.d("=========== Begin import from upload file ============");
						List<Essay> res = ExcelUtil.importFSXLStoObjectList(filePath);
						if (res!=null) {
							EssayDAOImpl eDaoImpl = new EssayDAOImpl();
							for (Essay essay : res) {
								eDaoImpl.addEssay(essay);
								Log.d("Add Essay: "+essay);
							}
						}
						Log.d("=========== End import from upload file ============");
						Log.d("All Import Done!");
					}
				}).start();;
        		
        		
                return "DataImEx";
            } catch (Exception e) {
            	Log.e("You failed to upload " + fileName + ": " + e.getMessage());
            	infoMsgs.add("上传文件 [ " + fileName + "] 失败，错误原因：" + e.getMessage());
        		modelMap.addAttribute("Infos", infoMsgs);
                return "DataImEx";
            }
        } else {
        	Log.e("Unable to upload. Check if file is empty.");
        	infoMsgs.add("没有选择文件，请重试！");
    		modelMap.addAttribute("Infos", infoMsgs);
            return "DataImEx";
        }
    }
	
	private static final String filePathPrefix = FileUtil.uploadDir;
	
	
	
	
	
	@RequestMapping(value="/singleUpload")
    public String singleUpload(){
    	return "singleUpload";
    }
	
    @RequestMapping(value="/singleSave", method=RequestMethod.POST )
    public @ResponseBody String singleSave(@RequestParam("file") MultipartFile file, @RequestParam("desc") String desc ){
    	System.out.println("File Description:"+desc);
    	String fileName = null;
    	if (!file.isEmpty()) {
            try {
                fileName = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                BufferedOutputStream buffStream = 
                        new BufferedOutputStream(new FileOutputStream(new File(filePathPrefix + fileName)));
                buffStream.write(bytes);
                buffStream.close();
                return "You have successfully uploaded " + fileName;
            } catch (Exception e) {
                return "You failed to upload " + fileName + ": " + e.getMessage();
            }
        } else {
            return "Unable to upload. File is empty.";
        }
    }
    
    @RequestMapping(value="/multipleUpload")
    public String multiUpload(){
    	return "multipleUpload";
    }
    
    @RequestMapping(value="/multipleSave", method=RequestMethod.POST )
    public @ResponseBody String multipleSave(@RequestParam("file") MultipartFile[] files){
    	String fileName = null;
    	String msg = "";
    	if (files != null && files.length >0) {
    		for(int i =0 ;i< files.length; i++){
	            try {
	                fileName = files[i].getOriginalFilename();
	                byte[] bytes = files[i].getBytes();
	                BufferedOutputStream buffStream = 
	                        new BufferedOutputStream(new FileOutputStream(new File(filePathPrefix + fileName)));
	                buffStream.write(bytes);
	                buffStream.close();
	                msg += "You have successfully uploaded " + fileName +"<br/>";
	            } catch (Exception e) {
	                return "You failed to upload " + fileName + ": " + e.getMessage() +"<br/>";
	            }
    		}
    		return msg;
        } else {
            return "Unable to upload. File is empty.";
        }
    }
}
