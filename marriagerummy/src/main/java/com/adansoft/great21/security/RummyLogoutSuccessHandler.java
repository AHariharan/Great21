package com.adansoft.great21.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

public class RummyLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler
		implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		 System.out.println("Logout invoked for  : "+ authentication.getPrincipal());
		 response.sendRedirect("");
		 super.onLogoutSuccess(request, response, authentication);
	}
	
}
