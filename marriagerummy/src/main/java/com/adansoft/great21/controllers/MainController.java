
package com.adansoft.great21.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	@Secured("ROLE_USER")
	@RequestMapping( value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView afterLogin(@AuthenticationPrincipal Authentication authentication)
	{
		String username = authentication.getName();
		System.out.println("USER Logged in as " + username);
		ModelAndView modelAndView = new ModelAndView("RummyPage");
		modelAndView.addObject("loggedinuser", username);
		return modelAndView;
	}

}
