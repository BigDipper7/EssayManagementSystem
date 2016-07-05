/**
 *
 */
package com.eva.me.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.loader.plan.exec.process.spi.ReturnReader;
import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.eva.me.dao.EssayDAOImpl;
import com.eva.me.dao.UserDAOImpl;
import com.eva.me.lucene.LuceneIndex;
import com.eva.me.lucene.SingleQaFileOperate;
import com.eva.me.model.Essay;
import com.eva.me.model.QAPairAdv;
import com.eva.me.model.User;
import com.eva.me.model.QAs;
import com.eva.me.service.EssayService;
import com.eva.me.service.UserService;
import com.eva.me.util.EssayUtil;
import com.eva.me.util.Log;

/**
 * @author phoen_000
 *
 */
@Controller
public class EditorDemoController {

	@RequestMapping(path={"/deamon"}, method=RequestMethod.GET)
	public ModelAndView getCKEditorDemoPage() {
		return new ModelAndView("CKEditorDemo", "essay", new Essay());
	}

	@RequestMapping(path={"/deamon"}, method=RequestMethod.POST)
	public String getAllPostData(@ModelAttribute Essay essay, ModelMap modelMap) {
		Log.i("handle post data...");
		Log.i(modelMap);

		modelMap.addAttribute("title", essay.getTitle());
		modelMap.addAttribute("author", essay.getAuthor());
		modelMap.addAttribute("content", essay.getContent());
		essay.author = EssayUtil.decode(essay.author);
		essay.category = EssayUtil.decode(essay.category);
		essay.content = EssayUtil.decode(essay.content);
		essay.title = EssayUtil.decode(essay.title);
		Log.i("=========New Essay======================");
		Log.i(essay);
		
		new EssayService().createEssay(essay);

		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("new thread:"+Thread.currentThread().getName());
				EssayUtil.saveAndIndex(essay);
			}
		}).start();
		return "redirect:/all";
	}

	@RequestMapping(path={"/update/{id}"}, method=RequestMethod.GET)
	public ModelAndView updateEssay(@PathVariable("id") Integer id, ModelMap modelMap) {
		Essay essayToUpdate = new EssayDAOImpl().getEssayById(id);
//		modelMap.addAttribute("essay", essayToUpdate);
//		return "CKEditorDemo";
		Log.e("essay origin:"+(Object)essayToUpdate.hashCode());
//		String title = essayToUpdate.getTitle();
//		try {
//			String newTitle = new String(title.getBytes("UTF-8"), "ISO-8859-1");
//			Log.e("==========================="+newTitle);
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		return new ModelAndView("CKEditorDemo", "essay", essayToUpdate);
	}

	@RequestMapping(path={"/update/{id}"}, method=RequestMethod.POST)
	public String handleUpdateEssayAction(@PathVariable("id") Integer id, ModelMap modelMap, @ModelAttribute Essay essay){
//		Log.e("essay after:"+(Object)essay.hashCode());
		if (essay==null) {
			Log.e("===============NULLLLLLLL===========================");
			return "redirect:/all";
		}
		essay.setId(id);
		
		essay.author = EssayUtil.decode(essay.author);
		essay.category = EssayUtil.decode(essay.category);
		essay.content = EssayUtil.decode(essay.content);
		essay.title = EssayUtil.decode(essay.title);
		Log.i("=========New Essay======================");
		Log.i(essay);
		
//		new EssayService().updateEssay(essay);
		new EssayDAOImpl().updateEssay(essay);

		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("new thread:"+Thread.currentThread().getName());
				EssayUtil.saveAndIndex(essay);
			}
		}).start();
		return "redirect:/all";
	}

	@RequestMapping(path={"/users/list"}, method=RequestMethod.GET)
	public ModelAndView retrieveDBUser() {
		List<User> users = new ArrayList<User>();

		users = new UserService().getAllUsers();
		Log.i(users);

		return new ModelAndView("UsersList", "users", users==null?new ArrayList<User>():users);
	}

	@RequestMapping(path={"/index/rebuild"}, method=RequestMethod.GET)
	public String rebuildAllIndex() {
		LuceneIndex luceneIndex = new LuceneIndex();
		luceneIndex.reBuildIndex();
		Log.d("all done");
		return "redirect:/main";
	}

	@RequestMapping(path="/corpus/delete", method=RequestMethod.GET)
	public String getCorpusDeletePage() {

		return "CorpusDelete";
	}

	@RequestMapping(path="/corpus/edit", method=RequestMethod.GET)
	public String getCorpusEditPage() {

		return "CorpusEdit";
	}

	@RequestMapping(path="/corpus/search", method=RequestMethod.GET)
	public String getCorpusSearchPage() {

		return "CorpusSearch";
	}

	@RequestMapping(path="/corpus/search", method=RequestMethod.POST)
	public String getCorpusSearchResultPage(ModelMap map, HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String qatxt = request.getParameter("question");
		Log.i("===============question is "+qatxt);

		List<QAs> list = new ArrayList<>();
		final int topN = 10;
		Vector<QAPairAdv> res = null;
		try {
			res = new LuceneIndex().searchOrigin(qatxt, topN);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (res==null) {
			return "CorpusSearch";
		}

		for (QAPairAdv qaPairAdv : res) {
			QAs tQAs = new QAs();
			tQAs.qus = qaPairAdv.doc.get("question");
			tQAs.ans = qaPairAdv.doc.get("answer");
			tQAs.sco = qaPairAdv.score;
			list.add(tQAs);
		}

		map.addAttribute("result", list);
		return "CorpusSearch";
	}

//	class QAs {
//		public String qus;
//		public String ans;
//		public float sco;
//	}


	@RequestMapping(path="/word/segment", method=RequestMethod.GET)
	public String getWordSegmentationPage() {
		return "WordSegmentation";
	}

	@RequestMapping(path="/word/segment", method=RequestMethod.POST)
	public String getWordSegmentationResultPage(ModelMap map, HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sent = request.getParameter("sentence");
		Log.i("===============sentence is "+sent);
		Vector<String> res = null;
		try {
			res = SingleQaFileOperate.getSinleQaFileOperate().segmentWord(sent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.i("====print ===================");
		Log.i(res);
		String result = "";
		for (String string : res) {
			result+=result.equals("")?string:" / "+string;
		}
		map.addAttribute("result", result);
		return "WordSegmentation";
	}
}
