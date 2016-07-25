/**
 * 
 */
package com.eva.me.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

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
import com.eva.me.util.Log;

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
                	infoMsgs.add("上传文件格式错误，仅支持 Excel！");
            		modelMap.addAttribute("Infos", infoMsgs);
					return "DataImEx";
				}
                
                final String filePath = filePathPrefix + fileName;
                byte[] bytes = file.getBytes();
                BufferedOutputStream buffStream = 
                        new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                buffStream.write(bytes);
                buffStream.close();
                
                Log.i("You have successfully uploaded " + fileName);
                infoMsgs.add("上传成功！  文件名： " + fileName);
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
            	infoMsgs.add("上传失败， 文件名：  " + fileName + "，错误原因： " + e.getMessage());
        		modelMap.addAttribute("Infos", infoMsgs);
                return "DataImEx";
            }
        } else {
        	Log.e("Unable to upload. Check if file is empty.");
        	infoMsgs.add("上传失败，请检查是否成功选择文件");
    		modelMap.addAttribute("Infos", infoMsgs);
            return "DataImEx";
        }
    }
	
	private static final String filePathPrefix = LuceneIndex.uploadDirectoryPath;
	
	
	
	
	
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
