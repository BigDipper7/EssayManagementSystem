/**
 * 
 */
package com.eva.me.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author phoen_000
 *
 */
@Controller
@RequestMapping("/hello")
public class HelloController {
	
	@RequestMapping(path={"","/","/test2","/test2/"},method=RequestMethod.GET)
	public String printHelloWorld(ModelMap modelMap) {
		modelMap.addAttribute("message", "hello u can believe");
		return "Hello";
	}
}
