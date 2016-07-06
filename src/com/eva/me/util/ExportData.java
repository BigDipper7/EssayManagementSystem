/**
 * 
 */
package com.eva.me.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

import com.eva.me.dao.EssayDAOImpl;
import com.eva.me.lucene.LuceneIndex;
import com.eva.me.model.Essay;
import com.eva.me.model.QAPair;
import com.google.gson.Gson;

/**
 * @author violi
 *
 */
public class ExportData {
	private static Map<String, List<Integer>> nameIdMapping = new HashMap<>();
	public static void saveAndIndex(Essay essay) {
		//pre process content in file
		//get json by essay
		String question = essay.getTitle();
		String answer = essay.getContent();
		
		System.out.println("question:"+question+"\nans:"+answer);

		question = EssayUtil.decode(question);
		answer = EssayUtil.decode(answer);
		answer = answer.replace("<p>", "");
		answer = answer.replace("</p>", "");
		answer = answer.replace("\r\n", "");
		System.out.println("question:"+question+"\nans:"+answer);

		//generate file content
		final String prefix = "<html><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"><head><title>Search</title></head><body><div class=\"q\">";
		final String middle = "<br></div><br><div class=\"a\">";
		final String end = "</div></body></html>";
		
		String fileContent = prefix + question + middle + answer + end;
		Log.i("=============html:====\n"+fileContent);
		
		// generate file path
		// store json file
		String fileName = EssayUtil.md5(question+answer);
		Log.i("========== MD5 =============="+fileName);
		String finalPath = LuceneIndex.fileDirectoryPath + File.separator;
		File f = new File(finalPath);
		if (!f.exists()) {
			f.mkdirs();
		}
		fileName += "_"+getCurrTimStr();
		finalPath+=fileName+".html";
		System.out.println("filepath:"+finalPath);
		
		Log.f("ID:"+essay.id+"\t fileName:"+fileName);
		if(nameIdMapping.containsKey(fileName)) {
			System.err.println("Contain name:"+fileName);
			nameIdMapping.get(fileName).add(essay.id);
		}
		else {
			List<Integer> vaList = new LinkedList<>();
			vaList.add(essay.id);
			nameIdMapping.put(fileName, vaList);
		}
		
//		// add index
//		LuceneIndex lIndex = new LuceneIndex();
//		lIndex.addIndex(finalPath);
		
		File file = new File(finalPath);
		try {
			file.createNewFile();
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
			try { 
			    out.write(fileContent);
			} finally { 
			    out.close();
			} 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getCurrTimStr() {
		Date today;
		String output;
		SimpleDateFormat formatter;
		String pattern = "yyyy_MM_dd__hh_mm_ss_SSS";

		formatter = new SimpleDateFormat(pattern);
		today = new Date();
		output = formatter.format(today);
//		System.out.println(pattern + " " + output);
		return output;
	}
	public static void main(String[] args) {
		List<Essay> list = new EssayDAOImpl().getAllEssayList();
		int i=0;
		for (Essay essay : list) {
			Log.i(String.format("----------Begin Essay [%d]---------------------------", i++));
			ExportData.saveAndIndex(essay);
			Log.i(String.format("----------END Essay [%d]---------------------------", i));
		}
		
		for (String key : ExportData.nameIdMapping.keySet()) {
			if (ExportData.nameIdMapping.get(key).size() > 1) {
				Log.e("=== value === filename:"+key);
				for (int id : ExportData.nameIdMapping.get(key)) {
					Log.e("\t===== id:"+id);
				}
			}
		}
	}

}
