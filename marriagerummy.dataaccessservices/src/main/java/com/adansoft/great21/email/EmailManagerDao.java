package com.adansoft.great21.email;

public interface EmailManagerDao {
	
	public void SendEmail(String receiverEmail, String messagetext,String subject);

}
