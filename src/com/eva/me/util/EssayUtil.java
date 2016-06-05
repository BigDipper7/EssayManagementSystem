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

		question = decode(question);
		answer = decode(answer);
		System.out.println("question:"+question+"ans:"+answer);
		
		QAPair temp = new QAPair(question,answer);
		Gson gson = new Gson();
		String tempJson = gson.toJson(temp);
//		String tempJson = question+answer;
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
	        // ����һ��MD5���ܼ���ժҪ  
	        MessageDigest md = MessageDigest.getInstance("MD5");  
	        // ����md5����  
	        md.update(str.getBytes());  
	        // digest()���ȷ������md5 hashֵ������ֵΪ8Ϊ�ַ�������Ϊmd5 hashֵ��16λ��hexֵ��ʵ���Ͼ���8λ���ַ�  
	        // BigInteger������8λ���ַ���ת����16λhexֵ�����ַ�������ʾ���õ��ַ�����ʽ��hashֵ  
	        pwd = new BigInteger(1, md.digest()).toString(16);  
	    } catch (NoSuchAlgorithmException e) {  
	        e.printStackTrace();  
	    }  
	    return pwd;  
	}
	
	
	
	/**
	 * �ɽ�����ת���� "&#" ��ͷ��htmlʵ�����
	 *
	 *
	 * @param str
	 * @return
	 */
	public static String encode(String str) {
	  char[] arrs = str.toCharArray();//Hex.encodeHex();
	  StringBuilder sb = new StringBuilder();
	  for (char c : arrs) {
	    // \\u ��ʾUnicode���롣
	    if (c >= '\u2E80' && c <= '\uFE4F') {//  [ ֻ������һ�� [ \u4e00-\u9fa5]�����պ�ͳһ�������֣�CJK Unified Ideographs�� [\u2E80-\uFE4F]
	      sb.append("&#").append((int)c).append(";");
	    } else {
	      sb.append(c);
	    }
	  }
	  return sb.toString();
	}


	/**
	 *  "&#" ��ͷ��htmlʵ����� ת�������ģ���ʵֻ�ǽ�����Ϊ5����������ת�����Կ�������Ӣ��ʵ�����ִ��󡣣�
	 *
	 * <option value="zh_CN">&#20013;&#25991; (&#31616;&#20307;)</option><option value="zh_TW">&#20013;&#25991; (&#32321;&#39636;)</option>
	 *
	 * @param str
	 * @return
	 */
	@Deprecated
	public static String decode(String str) {
	  String[] tmp = str.split(";&#|&#|;");
	  StringBuilder sb = new StringBuilder("");
	  for (int i = 0; i < tmp.length; i++) {
	    if (tmp[i].matches("\\d{5}")) {
	      sb.append((char) Integer.parseInt(tmp[i]));
	    } else {
	      sb.append(tmp[i]);
	    }
	  }
	  return sb.toString();
	}
	
	
	
	
	public static void main(String[] args) {
		Essay test = new EssayDAOImpl().getEssayById(8);
//		test.setTitle("")
		saveAndIndex(test);
	}
}
