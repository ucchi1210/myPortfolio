package com.example.application.mail;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;


@Component
public class MailSendController {
	private final MailSender mailSender;
	
	public MailSendController(MailSender mailSender) {
		this.mailSender=mailSender;
	}
	
	
	public void write1(String fromEmail ,String toEmail ,String subject ,String text ) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(fromEmail);
		msg.setTo(toEmail);
		msg.setSubject(subject);
		msg.setText(text);
		try {
			mailSender.send(msg);
			
		} catch (MailException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
				
	}
	

}
