package com.eva.me.controller;
/**
 * 
 */

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.eva.me.dao.EssayDAOImpl;
import com.eva.me.model.Essay;

/**
 * @author violi
 *
 */
@Controller
//@EnableWebMvc
//@RequestMapping(value="/json2")
public class JSONController {
	@RequestMapping(value="/t", method=RequestMethod.GET, headers="Accept=application/json", produces="application/text")
//	@RequestMapping
	public @ResponseBody List<Essay> getEssayList(HttpServletRequest request) {
		List<Essay> list = new EssayDAOImpl().getAllEssayList();
		return list;
	}
	
	/**
	 * TODO: Not good to use, error for without accept in headers
	 * @param request
	 * @return
	 */
	@Deprecated
	@RequestMapping(value="/t2", method=RequestMethod.GET, produces="application/text")
	public @ResponseBody List<Essay> getEssayList2(HttpServletRequest request) {
		List<Essay> list = new EssayDAOImpl().getAllEssayList();
		return list;
	}
	
	@RequestMapping(value="/t3", method=RequestMethod.GET)
	public @ResponseBody List<Essay> getEssayList3(HttpServletRequest request) {
		List<Essay> list = new EssayDAOImpl().getAllEssayList();
		return list;
	}
}
