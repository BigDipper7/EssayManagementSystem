/**
 * 
 */
package com.eva.me.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.eva.me.model.Essay;
import com.eva.me.model.User;
import com.eva.me.service.EssayService;
import com.eva.me.service.UserService;
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
	

	@RequestMapping(path={"/update/{id}"}, method=RequestMethod.GET)
	public ModelAndView updateEssay(@PathVariable("id") Integer id, ModelMap modelMap) {
		Essay essayToUpdate = new EssayDAOImpl().getEssayById(id);
//		modelMap.addAttribute("essay", essayToUpdate);
//		return "CKEditorDemo";
		Log.e("essay origin:"+(Object)essayToUpdate.hashCode());
		return new ModelAndView("CKEditorDemo", "essay", essayToUpdate);
	}
	
	@RequestMapping(path={"/deamon"}, method=RequestMethod.POST)
	public String getAllPostData(@ModelAttribute Essay essay, ModelMap modelMap) {
		Log.i("handle post data...");
		Log.i(modelMap);
		
		modelMap.addAttribute("title", essay.getTitle());
		modelMap.addAttribute("author", essay.getAuthor());
		modelMap.addAttribute("content", essay.getContent());
		new EssayService().createEssay(essay);
		return "DemoPres";
	}
	

	@RequestMapping(path={"/update/{id}"}, method=RequestMethod.POST)
	public String handleUpdateEssayAction(@PathVariable("id") Integer id, ModelMap modelMap, @ModelAttribute Essay essay){
//		Log.e("essay after:"+(Object)essay.hashCode());
		if (essay==null) {
			Log.e("===============NULLLLLLLL===========================");
			return "redirect:/all";
		}
		essay.setId(id);
		new EssayService().updateEssay(essay);
		return "redirect:/all";
	}
	
	@RequestMapping(path={"/users/list"}, method=RequestMethod.GET)
	public ModelAndView retrieveDBUser() {
		List<User> users = new ArrayList<User>();
		
		users = new UserService().getAllUsers();
		Log.i(users);
		
		return new ModelAndView("UsersList", "users", users==null?new ArrayList<User>():users);
	}
}
