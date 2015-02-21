package com.adansoft.great21.security;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.client.RestTemplate;

import com.adansoft.great21.controllers.FacadeControllerURLs;
import com.adansoft.great21.dataaccess.entities.UserAccounts;
import com.adansoft.great21.exceptions.DataAccessConfigException;
import com.adansoft.great21.router.FacadetoDataAccessMapper;

public class AuthenticationService implements UserDetailsService {

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
	
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		UserDetails response = null;
		UserAccounts result = null;
		
		try {
			URI url = new URI(mapper.getDataAccessURI() + "/"
					+ FacadeControllerURLs.DATAACCESS_AUTHBASE + "/"
					+ FacadeControllerURLs.FINDUSER);
			result = restTemplate.postForEntity(url, username, UserAccounts.class).getBody();
		
			if(result != null)
			{
			    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			    authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			    User user = new User(result.getNickName(), result.getPassword(), result.isEnabled(), true, true, true, authorities);
			    return user;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return response;
	}

}
