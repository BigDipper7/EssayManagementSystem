package com.eva.me.lucene;

import org.apache.lucene.document.Document;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;

public class SearchIndex {
	/**
	 * @param sentence(the query string)
	 * @param topN(the topN most similar answers)
	 * @return vector(the answer list)
	 */
	public Vector<String> search(String sentence, int topN) {
		LuceneIndex luceneIndex = new LuceneIndex();
		Vector<Document> hits = null;
		try {
			hits = luceneIndex.search(sentence, topN);
		} catch (IOException e) {
			System.err.println("get htis error....");
			e.printStackTrace();
		}

		/*String classpath = SearchIndex.class.getClassLoader().getResource("").getPath();
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(classpath+"/proper.properties"));
		} catch (FileNotFoundException e) {
			System.err.println("cannot find proper file...");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("read the properties file is error...");
			e.printStackTrace();
		}*/
		Vector<String> theBestAnswer = new Vector<String>();
		for(int i = 0; i < hits.size();i++)
		{
			theBestAnswer.add(hits.get(i).get("answer"));
		}
		return theBestAnswer;
	}
	public static void main(String[] args){
		SearchIndex si = new SearchIndex();
		Vector<String> res = si.search("如何操作", 10);
		for(int i=0;i<res.size();i++){
			System.out.println(res.get(i));
		}
		System.out.println("finish...");
	}
}
