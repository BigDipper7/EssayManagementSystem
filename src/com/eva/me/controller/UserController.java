/**
 * 
 */
package com.eva.me.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.eva.me.model.User;
import com.eva.me.service.UserService;
import com.eva.me.util.Log;
import com.eva.me.util.Config;

/**
 * @author phoen_000
 *
 */
@Controller
//@SessionAttributes(Config.SESSION_KEY_USER)
public class UserController {
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String showLoginPage(HttpServletRequest request, ModelMap modelMap) {
		HttpSession session = request.getSession(false);
		if (session !=null && session.getAttribute(Config.SESSION_KEY_USER) != null) {
			Log.i("===========has logged on=====================");
			Log.i(request.getSession().getAttribute(Config.SESSION_KEY_USER));
			return "redirect:main";
		}
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
	public String handleLoginAction(HttpServletRequest request,@ModelAttribute User user, ModelMap modelMap) {
		User resUser = new UserService().loginUser(user);
		if (resUser == null) {
//			user.setUsername("Wrong!!");
//			modelMap.addAttribute("User",user);
			List<String> errorMsgs = new ArrayList<String>();
			errorMsgs.add("登陆失败，用户名或密码错误！");
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
		
//		final String sid = request.getSession(true).getId();
//		Log.w("===========handleLoginAction session : sid="+sid);
		HttpSession session = request.getSession(true);
//		modelMap.addAttribute(Config.SESSION_KEY_USER,resUser);
		session.setAttribute(Config.SESSION_KEY_USER, resUser);
		
		return "redirect:main";
//		return new ModelAndView("Main", "user", resUser);
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	public String handleLogoutAction(HttpServletRequest request, ModelMap modelMap) {
		//TODO: do not know session sid always changing,
		//		request.getSession().setAttribute(Config.SESSION_USER_KEY, null);
//		request.getSession().removeAttribute(Config.SESSION_USER_KEY);
		HttpSession session = request.getSession(false);
		String sid = session.getId();
		Log.w("===========handleLogoutAction session : sid="+sid);
		session.invalidate();
//		session.
		
		//TODO: add redirect pass parameters
		//List<String> errorMsgs = new ArrayList<String>();
		//errorMsgs.add("登出成功！");
		//modelMap.addAttribute("Errors", errorMsgs);
		return "redirect:login";
	}

	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String showMainPage(ModelMap modelMap) {
		return "Main";
	}
}
