package com.adansoft.great21.email;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

public class EmailConfigurator implements Runnable {
	
	private JavaMailSender mailSender;

	private MimeMessagePreparator preparator;
	
	public EmailConfigurator(JavaMailSender jMailSender,
			MimeMessagePreparator preparator) {

		this.mailSender = jMailSender;
		this.preparator = preparator;

	}

	public void run() {
		this.mailSender.send(this.preparator);
	}

}
