/**
 * 
 */
package com.eva.me.model;

import java.util.List;

import org.apache.lucene.document.Document;


/**
 * @author violi
 *
 */
public class QAPairAdv {
	public Document doc;
	public float score;
	
	/**
	 * 
	 */
	public QAPairAdv(Document doc, float scr) {
		this.doc = doc;
		this.score = scr;
	}
}
