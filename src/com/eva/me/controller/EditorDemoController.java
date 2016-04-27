/**
 * 
 */
package com.eva.me.controller;

import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.eva.me.model.Essay;
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
	
	
	@RequestMapping(path={"/handle"}, method=RequestMethod.POST)
	public String getAllPostData(@ModelAttribute Essay essay, ModelMap modelMap) {
		Log.i("handle post data...");
		Log.i(modelMap);
		
		modelMap.addAttribute("title", essay.getTitle());
		modelMap.addAttribute("author", essay.getAuthor());
		modelMap.addAttribute("content", essay.getContent());
		
		return "DemoPres";
	}
}
