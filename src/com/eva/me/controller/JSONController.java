package com.eva.me.controller;
/**
 * 
 */

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.queryparser.surround.query.SrndPrefixQuery;
import org.apache.tomcat.websocket.WsRemoteEndpointAsync;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.eva.me.dao.EssayDAOImpl;
import com.eva.me.lucene.LuceneIndex;
import com.eva.me.model.Essay;
import com.eva.me.model.QAPair;
import com.eva.me.model.QAPairAdv;
import com.eva.me.util.Log;


/**
 * @author violi
 *
 */
@Controller
//@EnableWebMvc
@RequestMapping(value="/json")
public class JSONController {
	@RequestMapping(value="/t", method=RequestMethod.GET, headers="Accept=application/json", produces="application/text")
//	@RequestMapping
	public @ResponseBody List<Essay> getEssayList(HttpServletRequest request) {
		List<Essay> list = new EssayDAOImpl().getAllEssayList();
		return list;
	}
	
	/**
	 * TODO: Not good to use, error for without accept in headers
	 * @param request
	 * @return
	 */
	@Deprecated
	@RequestMapping(value="/t2", method=RequestMethod.GET, produces="application/text")
	public @ResponseBody List<Essay> getEssayList2(HttpServletRequest request) {
		List<Essay> list = new EssayDAOImpl().getAllEssayList();
		return list;
	}
	
	@RequestMapping(value="/t3", method=RequestMethod.GET)
	public @ResponseBody List<Essay> getEssayList3(HttpServletRequest request) {
		List<Essay> list = new EssayDAOImpl().getAllEssayList();
		return list;
	}
	
	@RequestMapping(value="/query")
	public @ResponseBody Test getTestByQuery(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Test test = new Test();

		String uid = request.getParameter("uid");
		String qatxt = request.getParameter("qatxt");
//		String count = request.getParameter("count");
		
		Log.i("=========get params: uid:"+uid+"|qatxt:"+qatxt+"|");
		test.uid = uid;
		test.question = qatxt;
//		List<Essay> list = new EssayDAOImpl().getAllEssayList();
		List<QAs> list = new ArrayList<>();
		final int topN = 3;
		Vector<QAPairAdv> res = null;
		try {
			res = new LuceneIndex().searchOrigin(qatxt, topN);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (QAPairAdv qaPairAdv : res) {
			QAs tQAs = new QAs();
			tQAs.qus = qaPairAdv.doc.get("question");
			tQAs.ans = qaPairAdv.doc.get("answer");
			tQAs.sco = qaPairAdv.score;
			list.add(tQAs);
		}
		test.answers = list;
		return test;
	}
	
	class Test{
		public String uid = "uid";
		public String question = "";
//		public List<Essay> answers = new ArrayList<>();
		public List<QAs> answers = new ArrayList<>();
	}
	
	class QAs {
		public String qus;
		public String ans;
		public float sco;
	}
	
	
	
	class AjaxAllEssayResult {
		public List<Essay> data = new ArrayList<>();
		public int recordsTotal = 0;
		public int recordsFiltered = 0;
		
	}
	
	@RequestMapping(value="/allCorpus")
	public @ResponseBody AjaxAllEssayResult getAjaxAllEssayList(HttpServletRequest request) {
		
		String paramStart = request.getParameter("start");
		String paramLength = request.getParameter("length");
		String paramSearchTxt = request.getParameter("search[value]");
		
		Log.i("========= Retrieve Parameter =========");
		Log.i("==== paramStart: ["+paramStart+"]");
		Log.i("==== paramLength: ["+paramLength+"]");
		Log.i("==== paramSearchTxt: ["+paramSearchTxt+"]");
		
		int start = Integer.parseInt(paramStart);
		int length = Integer.parseInt(paramLength);
		
		EssayDAOImpl impl = new EssayDAOImpl();
		List<Essay> list = null;
		if (StringUtils.isEmpty(paramSearchTxt)) {
			Log.i("paramTxt is empty");
			list = impl.getEssayListWithLimit(start, length);
		}else {
			list = impl.getEssayListWithLimit(start, length, paramSearchTxt);
		}
		long count = impl.getAllCount();
		
		AjaxAllEssayResult result = new AjaxAllEssayResult();
		
		if (list == null) {
			return result;
		}
		
//		for (Essay essay : list) {
//			List<String> temp = new ArrayList<>();
//			temp.add(essay.id+"");
//			temp.add(essay.title);
//			temp.add(essay.content);
//			temp.add(essay.author);
//			result.data.add(temp);
//		}
		
		result.data = list;
		result.recordsTotal = (int) count;
		result.recordsFiltered = (int) count;
		
		
		return result;
	}
	
}
