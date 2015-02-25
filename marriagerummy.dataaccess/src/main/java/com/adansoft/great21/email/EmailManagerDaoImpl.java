package com.adansoft.great21.email;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

public class EmailManagerDaoImpl implements EmailManagerDao {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private SimpleMailMessage templateMessage;
	
	@Autowired
	private TaskExecutor taskExecutor;
	
	public void SendEmail(final String receiverEmail, final String messagetext,
			final String subject) {
		SimpleMailMessage message = new SimpleMailMessage(this.templateMessage);
		message.setTo(receiverEmail);
		message.setText(messagetext);
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(receiverEmail);
				message.setText(messagetext, true);
				message.setSubject(subject);
				
			}
		};
		try {
			taskExecutor.execute(new EmailConfigurator(mailSender, preparator));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
