/**
 * 
 */
package com.eva.me.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.eva.me.model.Essay;

/**
 * @author violi
 *
 */
@Controller
@RequestMapping(path={"/admin"})
public class AdminManageController {

	@RequestMapping(path={"/showusrs"}, method=RequestMethod.GET)
	public ModelAndView getCKEditorDemoPage() {
		return new ModelAndView("CKEditorDemo", "essay", new Essay());
	}
}
