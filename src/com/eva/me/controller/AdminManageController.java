/**
 * 
 */
package com.eva.me.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.eva.me.dao.EssayDAOImpl;
import com.eva.me.dao.UserDAOImpl;
import com.eva.me.model.Essay;
import com.eva.me.model.User;
import com.eva.me.util.Config;
import com.eva.me.util.EssayUtil;
import com.eva.me.util.Log;

/**
 * @author violi
 *
 */
@Controller
@RequestMapping(path={"/admin"})
public class AdminManageController {

	@RequestMapping(path={"/showusrs"}, method=RequestMethod.GET)
	public String getCKEditorDemoPage(HttpServletRequest request, ModelMap modelMap) {
		List<User> allUsers = new UserDAOImpl().getAllUsersList();
		modelMap.addAttribute("Users",allUsers);
		
		return isLogin(request)? "AllUsers" : "redirect:/login";
	}

	@RequestMapping(path={"/persist"}, method=RequestMethod.GET)
	public String persistAllDBInFiles() {
		List<Essay> allEssays = new EssayDAOImpl().getAllEssayList();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				int i=0;
				for (Essay essay : allEssays) {
					Log.i("===save essay==== [ "+i+" ]");
					Log.i(essay);
					EssayUtil.saveAndIndex(essay);
					Log.i("===save essay success====");
					i++;
				}
				
			}
		}).start();
		return "redirect:/all";
	}
	
	public boolean isLogin(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return session!=null && session.getAttribute(Config.SESSION_KEY_USER) != null;
	}
}
