package com.adansoft.great21.ulitity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashingUtility {
	
	public static String encodeMessage(String message)
	{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(message);
	}

}
