package com.eva.me.lucene;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.*;

import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.cfg.DefaultConfig;
import org.wltea.analyzer.dic.Dictionary;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class QaFileOperate {

	/**
	 * @param sentence
	 * @return List(every item is one word)
	 * @throws IOException
	 */
	public Vector<String> segmentWord(String sentence)
			throws IOException
	{
		System.out.println(sentence);
		Dictionary.initial(DefaultConfig.getInstance());
		Analyzer analyzer = new IKAnalyzer(true);
		StringReader reader = new StringReader(sentence);
		TokenStream ts = analyzer.tokenStream("", reader);
		CharTermAttribute term = ts.getAttribute(CharTermAttribute.class);
		Vector<String> wordList = new Vector<String>();
		while(ts.incrementToken())
		{
			wordList.add(term.toString());
			System.out.println(term.toString());
		}
		reader.close();
		return wordList;
	}

	/**
	 * delete some folder
	 * @param folderPath
	 */
	public void deleteFolder(String folderPath){
		File file = new File(folderPath);
		if( file.isFile() ){
			file.delete();
		}
		else{
			String[] fileList = file.list();
			if(fileList != null) {
				for (int i = 0; i < fileList.length; i++) {
					//File oneFile = new File(folderPath+"\\"+fileList[i]);
					deleteFolder(folderPath + "/" + fileList[i]);
//					deleteFolder(folderPath + File.separator + fileList[i]);
				}
			}
		}
	}

	/**
	 * copy file from target to des
	 * @param targetFilePath
	 * @param desFilePath
	 */
	public void copyFile(File targetFile, String desFilePath) {
		FileInputStream fin = null;
		FileOutputStream fout = null;
		try {
			fin = new FileInputStream(targetFile);
			final String desFilePathWhole = desFilePath+File.separator+targetFile.getName();
			File fdir = new File(desFilePath);
			if (!fdir.exists()) {
				fdir.mkdirs();
			}
			File fo = new File(desFilePathWhole);
			if (!fo.exists()) {
				fo.createNewFile();
			}else{
				System.err.println("目标文件存在，请检查："+desFilePathWhole);
				return;
			}
			fout = new FileOutputStream(fo);
			
			int in = fin.read();
			while (in != -1) {
				fout.write(in);
				in = fin.read();
			}
			System.out.println("Copy from:"+targetFile.getAbsolutePath()+"\n to:"+desFilePathWhole+"\n Success!");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (fin!=null) {
				try {
					fin.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (fout!=null) {
				try {
					fout.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @param file
	 * @return String(the file's content)
	 */
	public String getFileContent(File file){
		try {
			if(file.isFile()&&file.exists()){
				InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
				BufferedReader bufferedReader = new BufferedReader(reader);
				String line = "";
				StringBuffer text = new StringBuffer("");
				while((line = bufferedReader.readLine())!=null){
					text.append(line);
				}
				return text.toString();
			}
			else {
				System.err.println("cannot find the file:"+file.getName());
				return null;
			}
		}
		catch(Exception e){
			System.err.println("read file error...");
			e.printStackTrace();
		}
		return null;
	}

	public ContainerFactory containerFactory = new ContainerFactory() {
		public Map createObjectContainer() {
			return new LinkedHashMap();
		}
		public java.util.List creatArrayContainer() {
			return new LinkedList();
		}
	};


	/**
	 * @param file(one qa file)
	 * @param tag(the tag content, including "answer" or "question")
	 * @return String(the content of the tag in the file)
	 */
	public String getFileContentOfTag(File file, String tag){
		String fileContent = getFileContent(file);
		JSONParser parser = new JSONParser();
		Map jsonMap = null;
		try {
			jsonMap = (Map)parser.parse(fileContent,containerFactory);
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			System.err.println("json("+file.getName()+") is error...");
			e.printStackTrace();
			return "ERROR(Json error)";
		}
		Iterator iterator = jsonMap.entrySet().iterator();
		String question = "";
		String answer = "";
		while(iterator.hasNext()){
			Map.Entry entry = (Map.Entry)iterator.next();
			//System.out.println(entry.getKey()+"=>"+entry.getValue());
			if( entry.getKey().toString().equals(tag) ){
				question = entry.getValue().toString();
				return question;
			}
			else if( entry.getKey().toString().equals(tag) ){
				answer = entry.getValue().toString();
				return answer;
			}
		}
		return "NULL";
	}

	public static void main(String[] args) throws IOException {
		QaFileOperate a = new QaFileOperate();
		System.out.println(a.segmentWord("马尔科夫模型"));
//		a.addIndex("C:\\Users\\violi\\Desktop\\毕设\\Search\\Search\\src\\main\\resources\\document\\allFaq\\00aba82892252b502cabc9db11982fa5.txt");
        QaFileOperate qaFileOperate = SingleQaFileOperate.getSinleQaFileOperate();
        System.out.println(qaFileOperate.getFileContent(new File("C:\\Users\\violi\\Desktop\\毕设\\Search\\Search\\src\\main\\resources\\document\\allFaq\\00aba82892252b502cabc9db11982fa5.txt")));
        System.out.println(qaFileOperate.getFileContentOfTag(
        		new File("C:\\Users\\violi\\Desktop\\毕设\\Search\\Search\\src\\main\\resources\\document\\allFaq\\00aba82892252b502cabc9db11982fa5.txt")
        		, "question"));
        
        System.out.println(qaFileOperate.getFileContent(new File("C:\\Users\\violi\\Desktop\\毕设\\Search\\Search\\src\\main\\resources\\document\\allFaq\\00aba82892252b502cabc9db11982fa5.txt")));
        
	}
}
