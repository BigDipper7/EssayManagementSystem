/**
 * 
 */
package com.eva.me.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.eva.me.dao.EssayDAOImpl;
import com.eva.me.model.Essay;

/**
 * @author violi
 *
 */
public class ImportData {
	public static void main(String[] args) {
		final String string = "";
		String pathname = "D:\\test\\ALL_QA_TXT.txt";
		File file = new File(pathname);
		EssayDAOImpl impl = new EssayDAOImpl();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       // process the line. 
//		    	line = new String(line.getBytes("GB2312"), "UTF-8");
		    	String[] qas = line.split("\t");
		    	System.out.println(qas[0]+" --- "+qas[1]+"\n\n");
		    	Essay essay = new Essay();
		    	essay.title = qas[0];
		    	essay.content = qas[1];
		    	essay.author = "";
		    	essay.category = "";
		    	impl.addEssay(essay);
		    } 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}
