package com.masterBook.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.masterBook.service.UserService;
import com.masterBook.model.UserDataVO;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="registerUser",method=RequestMethod.POST)
	public String registerUser(@ModelAttribute() UserDataVO userDataRegForm) {
		userService.registerUser(userDataRegForm);
		return "redirect:/mainPage";
	}
	
	@RequestMapping(value="verifyUser",method=RequestMethod.POST)
	public String verifyUser(HttpSession session,@RequestParam("email") String email , @RequestParam("password") String password ,Model model) {
		String page;
		UserDataVO data = new UserDataVO();
		data.setEmail(email);
		data.setPassword(password);
		UserDataVO userData = userService.verifyUser(data);
		
		if(userData != null) {
			page = "redirect:/homePage";
			session.setAttribute("userData",userData);
		}else {
			page = "redirect:/mainPage";
			model.addAttribute("userDataRegForm",new UserDataVO());
		}
		return page;
	}
	
	@RequestMapping(value="homePage")			//send User to home page from other jsp
	public String userHomePage() {
		return "UserHome";
	}
	
	@RequestMapping(value="logout")
	public ModelAndView logout(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		session.removeAttribute("userData");			//remove user Data from session when logout
		mv.setViewName("UserLoginRegister");
		mv.addObject("userDataRegForm", new UserDataVO());
		return mv;
	}
	
	@RequestMapping(value="mainPage")
	public ModelAndView mainPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("UserLoginRegister");
		mv.addObject("userDataRegForm", new UserDataVO());
		return mv;
	}
	
	@RequestMapping(value="/")
	public String firstMethod() {
		return "redirect:/mainPage";
	}
}
