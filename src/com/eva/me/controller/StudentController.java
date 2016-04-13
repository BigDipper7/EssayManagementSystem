/**
 * 
 */
package com.eva.me.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.eva.me.model.Student;

/**
 * @author phoen_000
 *
 */
@RequestMapping("/student")
@Controller
public class StudentController {
	
	@RequestMapping(value={""}, method=RequestMethod.GET)
	public ModelAndView student() {
		return new ModelAndView("Student", "command", new Student());
	}
	
	@RequestMapping(value={"/add"},method=RequestMethod.POST)
	public String addStudent(@ModelAttribute("SpringWeb")Student student, ModelMap modelMap) {
		modelMap.addAttribute("account",student.getAccount());
		modelMap.addAttribute("name", student.getName());
		modelMap.addAttribute("age", student.getAge());
		return "Result";
	}
	
}
