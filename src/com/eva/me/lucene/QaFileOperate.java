package com.eva.me.lucene;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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
	}
}
