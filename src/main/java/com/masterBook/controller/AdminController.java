package com.masterBook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.masterBook.service.AdminService;
import com.masterBook.model.AdminDataVO;

@Controller
public class AdminController {
	
	@RequestMapping(value="verifyAdmin",method=RequestMethod.POST)
	public ModelAndView verifyAdmin(@RequestParam("userName") String userName , @RequestParam("password") String password) {
		AdminService service = new AdminService();
		AdminDataVO adminData = new AdminDataVO();
		adminData.setUserName(userName);
		adminData.setPassword(password);
		ModelAndView mv = new ModelAndView();
		if(service.verifyAdmin(adminData)) {
			mv.setViewName("AdminHomePage");
		}else {
			mv.setViewName("AdminLogin");
		}
		return mv;
	}
}
