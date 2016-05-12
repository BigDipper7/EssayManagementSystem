/**
 * 
 */
package com.eva.me.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.eva.me.model.User;
import com.eva.me.service.UserService;
import com.eva.me.util.Log;

/**
 * @author phoen_000
 *
 */
@Controller
public class UserController {
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String showLoginPage(ModelMap modelMap) {
//		if (user == null) {
//			Log.e("user is null~");
//			user = new User();
//		}
		User user = new User();
//		user.setUsername("this is new username");
//		return new ModelAndView("Login", "User", user);
		modelMap.addAttribute("User",user);
		return "Login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String handleLoginAction(@ModelAttribute User user, ModelMap modelMap) {
		User resUser = new UserService().loginUser(user);
		if (resUser == null) {
//			user.setUsername("Wrong!!");
//			modelMap.addAttribute("User",user);
			List<String> errorMsgs = new ArrayList<String>();
			errorMsgs.add("µÇÂ½Ê§°Ü£¬ÓÃ»§Ãû»òÃÜÂë´íÎó£¡");
			modelMap.addAttribute("Errors", errorMsgs);
			user.setPassword("");
			modelMap.addAttribute("User", user);
			return "Login";
//			return new ModelAndView("Login", "User", new User());
		}
		
//		modelMap.addAttribute("id", resUser.getId());
//		modelMap.addAttribute("username", resUser.getUsername());
//		modelMap.addAttribute("password", resUser.getPassword());
//		modelMap.addAttribute("email", resUser.getEmail());
		
		modelMap.addAttribute("user",resUser);
		return "Main";
//		return new ModelAndView("Main", "user", resUser);
	}
}
