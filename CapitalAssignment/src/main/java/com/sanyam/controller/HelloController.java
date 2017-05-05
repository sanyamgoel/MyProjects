package com.sanyam.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sanyam.entity.User;
import com.sanyam.entity.UserDetails;
import com.sanyam.service.UserService;
import com.sanyam.utility.Constants;

@Controller
@RequestMapping("/hello")
public class HelloController {

	@Autowired
	private UserService userService;
	
	//for register webpage
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView(Constants.REGISTER);
		mav.addObject("userDetails", new UserDetails());
		return mav;

	}
	
	//for register user request
	@RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	public ModelAndView addUser(HttpSession session, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("userDetails") UserDetails user) {
		String message = userService.register(user);
		ModelAndView mav = null;
		if(message.equalsIgnoreCase(Constants.DUPLICATE)){
			mav = new ModelAndView(Constants.REGISTER);
			mav.addObject("userDetails", new UserDetails());
			request.setAttribute(Constants.MESSAGE, "Username already exists. Try other username!");
		}
		else if(message.equalsIgnoreCase(Constants.FAILURE)){
			mav = new ModelAndView(Constants.REGISTER);
			mav.addObject("userDetails", new UserDetails());
			request.setAttribute(Constants.MESSAGE, "Internal Server Error. Please try again!");
		}
		else{
			if(session.getAttribute(Constants.USERNAME)!=null){
				mav = new ModelAndView(Constants.REGISTER);
				mav.addObject("userDetails", new UserDetails());
				request.setAttribute(Constants.MESSAGE, message);
			}
			else{
				mav = new ModelAndView(Constants.WELCOME);
				request.setAttribute(Constants.FIRSTNAME, user.getFirstname());
				session.setAttribute(Constants.USERNAME, user.getUsername());
				session.setAttribute(Constants.FIRSTNAME, user.getFirstname());
			}
		}
		return mav;

	}

	//for login web page
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = null;
		if(session.getAttribute(Constants.USERNAME)!=null){
			mav = new ModelAndView(Constants.WELCOME);
			request.setAttribute(Constants.FIRSTNAME, session.getAttribute(Constants.FIRSTNAME));
		}
		else{
			mav = new ModelAndView(Constants.LOGIN);
			mav.addObject("user", new User());
		}
		return mav;
	}

	//for user login request
	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	public ModelAndView loginProcess(HttpSession session,HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User userCheck) {

		ModelAndView mav = null;
		UserDetails userDetails = userService.validateUser(userCheck);
		if (null != userDetails) {
			mav = new ModelAndView(Constants.WELCOME);
			request.setAttribute(Constants.FIRSTNAME, userDetails.getFirstname());
			session.setAttribute(Constants.USERNAME, userDetails.getUsername());
			session.setAttribute(Constants.FIRSTNAME, userDetails.getFirstname());
		}
		else {
			mav = new ModelAndView(Constants.LOGIN);
			request.setAttribute(Constants.MESSAGE, "Username or Password is wrong!!");
		}
		return mav;
	}
	
	//for logout
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		session.invalidate();
		ModelAndView mav = new ModelAndView(Constants.LOGIN);
		mav.addObject("user", new User());
		return  mav;

	}
}