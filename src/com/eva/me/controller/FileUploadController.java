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
	

    @RequestMapping(value="/singleSaveExcel", method=RequestMethod.POST )
    public String singleSaveOnlyExcel(@RequestParam("file") MultipartFile file, @RequestParam("desc") String desc, ModelMap modelMap ){
		List<String> infoMsgs = new ArrayList<>();
    	
    	System.out.println("File Description:"+desc);
    	String fileName = null;
    	if (!file.isEmpty()) {
            try {
                fileName = file.getOriginalFilename();
                if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")) {
                	infoMsgs.add("Upload File Invalid, file type not support, Excel Only!");
            		modelMap.addAttribute("Infos", infoMsgs);
					return "DataImEx";
				}
                byte[] bytes = file.getBytes();
                BufferedOutputStream buffStream = 
                        new BufferedOutputStream(new FileOutputStream(new File(filePathPrefix + fileName)));
                buffStream.write(bytes);
                buffStream.close();
                
                infoMsgs.add("You have successfully uploaded " + fileName);
        		modelMap.addAttribute("Infos", infoMsgs);
                return "DataImEx";
            } catch (Exception e) {
            	infoMsgs.add("You failed to upload " + fileName + ": " + e.getMessage());
        		modelMap.addAttribute("Infos", infoMsgs);
                return "DataImEx";
            }
        } else {
        	infoMsgs.add("Unable to upload. Check if file is empty.");
    		modelMap.addAttribute("Infos", infoMsgs);
            return "DataImEx";
        }
    }
	
	private static final String filePathPrefix = "d:\\test\\";
	
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
