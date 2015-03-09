package com.adansoft.great21.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class RummyUser extends User {

	private static final long serialVersionUID = 306664868897398841L;

	private long userid;
	private String nickname;
	private String emailaddr;

	public RummyUser(String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		
	}

	public RummyUser(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities,long userid,String nickname,String emailaddress) {
		super(username, password, enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, authorities);
		this.userid = userid;
		this.nickname = nickname;
		this.emailaddr = emailaddress;
		
	}

	public long getUserid() {
		return userid;
	}

	public String getNickname() {
		return nickname;
	}

	public String getEmailaddr() {
		return emailaddr;
	}

	
	
}
