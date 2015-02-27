
package com.adansoft.great21.controllers;

import java.net.URI;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.adansoft.great21.dataaccess.schemas.ActivateAccountRequest;
import com.adansoft.great21.exceptions.DataAccessConfigException;
import com.adansoft.great21.router.FacadetoDataAccessMapper;

@Controller
public class MainController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	FacadetoDataAccessMapper mapper;
	
	
	@PostConstruct
	public void verifyGameIndexerConfig()
	{
		try
		{
		if(mapper.getDataAccessURI() == null)
		{
			System.out.println("Failed to get DataAccess config .. exiting");
			System.exit(0);
		}
		System.out.println("Data Access from Authentication Service: " + mapper.getDataAccessURI());
		}catch(DataAccessConfigException ex)
		{
			ex.printStackTrace();
			System.exit(0);
		} catch (Exception e) {
		   e.printStackTrace();
		   System.exit(0);
		}
	}
	
	
	@Secured("ROLE_USER")
	@RequestMapping( value = "/rummy", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView afterLogin(@AuthenticationPrincipal Authentication authentication)
	{
		String username = authentication.getName();
		System.out.println("USER Logged in as " + username);
		ModelAndView modelAndView = new ModelAndView("RummyPage");
		modelAndView.addObject("loggedinuser", username);
		return modelAndView;
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = "/loginFailure", method = {RequestMethod.GET, RequestMethod.POST })
	public ModelAndView onLoginFailure(HttpServletRequest request)
	{
		String reason = (String) request.getSession().getAttribute("reason");
		System.out.println("The reason inside Login Failure : " + reason);
		ModelAndView modelAndView = new ModelAndView("LoginFailure");
		modelAndView.addObject("FailReason",reason);
		return modelAndView;
	}
	
	@Secured("ROLE_USER")
	@RequestMapping( value = "/activate/account", method = {RequestMethod.GET, RequestMethod.POST })
	public ModelAndView activateAccount(@RequestParam(value="id") String emailid,@RequestParam(value="authtoken") String activationCode)
	{
		ModelAndView modelAndView = new ModelAndView("ActivateSuccess");
		ActivateAccountRequest request = new ActivateAccountRequest(emailid,activationCode);
		String result = null;
		try {
			URI url = new URI(mapper.getDataAccessURI() + "/"
					+ FacadeControllerURLs.DATAACCESS_AUTHBASE + "/"
					+ FacadeControllerURLs.ACTIVATE_ACCOUNT);
			result = restTemplate.postForEntity(url, request, String.class).getBody();
			if(result.equals("Success"))
			{
				modelAndView.addObject("activationlink","Success");
			}
			else
			{
				modelAndView.addObject("activationlink","Failure");
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}

		return modelAndView;
	}

}
