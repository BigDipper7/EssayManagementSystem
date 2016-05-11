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

import com.eva.me.model.User;
import com.eva.me.service.UserService;

/**
 * @author phoen_000
 *
 */
@Controller
public class UserController {
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView showLoginPage() {
		User user = new User();
//		user.setUsername("this is new username");
		return new ModelAndView("Login", "User", user);
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String handleLoginAction(@ModelAttribute User user, ModelMap modelMap) {
		User resUser = new UserService().loginUser(user);
		if (resUser == null) {
			return "redirect:login";
		}
		
		modelMap.addAttribute("id", resUser.getId());
		modelMap.addAttribute("username", resUser.getUsername());
		modelMap.addAttribute("password", resUser.getPassword());
		modelMap.addAttribute("email", resUser.getEmail());
		
		return "Main";
	}
}
