/**
 * 
 */
package com.eva.me.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.eva.me.dao.EssayDAOImpl;
import com.eva.me.model.Essay;
import com.eva.me.model.QAPair;
import com.google.gson.Gson;

/**
 * @author violi
 *
 */
public class EssayUtil {
	public static void saveAndIndex(Essay essay) {
		String question = essay.getTitle();
		String answer = essay.getContent();
		
		System.out.println("question:"+question+"ans:"+answer);
		try {
			question = new String(question.getBytes(), "UTF-8");
			answer = new String(answer.getBytes("iso8859-1"),"GB2312");
			System.out.println("question:"+question+"ans:"+answer);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
//		QAPair temp = new QAPair(question,answer);
//		Gson gson = new Gson();
//		String tempJson = gson.toJson(temp);
		String tempJson = question+answer;
		Log.i("=============json: "+tempJson);
		
		final String fileName = md5(question+answer);
		Log.i("resMD========================"+fileName);
		final String finalPath = "d://"+fileName+".txt";
		try {
			File fout = new File(finalPath);
			if (!fout.exists()) {
					fout.createNewFile();
			}
			FileOutputStream outputStream = new FileOutputStream(fout);
			outputStream.write(tempJson.getBytes());
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String md5(String str){  
	    String pwd = null;  
	    try {  
	        // 生成一个MD5加密计算摘要  
	        MessageDigest md = MessageDigest.getInstance("MD5");  
	        // 计算md5函数  
	        md.update(str.getBytes());  
	        // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符  
	        // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值  
	        pwd = new BigInteger(1, md.digest()).toString(16);  
	    } catch (NoSuchAlgorithmException e) {  
	        e.printStackTrace();  
	    }  
	    return pwd;  
	}
	
	public static void main(String[] args) {
		Essay test = new EssayDAOImpl().getEssayById(8);
//		test.setTitle("")
		saveAndIndex(test);
	}
}
