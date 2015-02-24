package com.adansoft.great21.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class AuthSuccessHandler implements AuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	public void onAuthenticationSuccess(HttpServletRequest req,
			HttpServletResponse resp, Authentication auth) throws IOException,
			ServletException {
		System.out.println(auth.getPrincipal().getClass());
		String targetURL = "/rummy";
		redirectStrategy.sendRedirect(req,resp,targetURL);
	}

}
