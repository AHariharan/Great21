package com.adansoft.great21.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class AuthFailureHandler implements AuthenticationFailureHandler {
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	public void onAuthenticationFailure(HttpServletRequest req,
			HttpServletResponse resp, AuthenticationException authException)
			throws IOException, ServletException {
		  System.out.println("Failure Reason :" + authException.getMessage());
		  HttpSession session = req.getSession(false);
		  session.setAttribute("reason", authException.getMessage());
		  String targetURL = "/loginFailure";
		  redirectStrategy.sendRedirect(req, resp, targetURL);

	}

}
