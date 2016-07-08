/**
 * 
 */
package com.eva.me.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.eva.me.dao.EssayDAOImpl;
import com.eva.me.lucene.LuceneIndex;
import com.eva.me.model.Essay;

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
		if(answer.endsWith("<br>"))
			answer = answer.substring(0, answer.length()-4);
		if(question.endsWith("<br>"))
			question = question.substring(0, question.length()-4);
		System.out.println("question:"+question+"\nans:"+answer);

		
		//generate file content
		final String prefix = "<html><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"><head><title>Search</title></head><body><div class=\"q\">";
		final String middle = "</div><br><div class=\"a\">";
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
		fileName += "_"+TimeUtil.getCurrTimStr();
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
		
		FileUtil.writeUTF8(finalPath, fileContent);
	}
	
	public static void exportListToFS(List<Essay> list) {
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
	
	public static void main(String[] args) {
//		List<Essay> list = new EssayDAOImpl().getAllEssayList();
		try {
			FileUtil.delete(new File(LuceneIndex.fileDirectoryPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		exportListToFS(list);
		Essay e = new Essay();
		e.content = "啊哈哈啥事<br>";
		e.title = "是的么<br>";
				
		saveAndIndex(e);
	}

}
