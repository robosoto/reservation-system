package com.goeazycarrent.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import com.goeazycarrent.service.email.Mail;
import com.goeazycarrent.service.email.MailServiceImpl;

import jakarta.mail.internet.MimeMessage;

@ExtendWith(MockitoExtension.class)
public class MailServiceTest {
	
	@InjectMocks
	MailServiceImpl service;
	
	@Mock
	JavaMailSender javaMailSender; 
	
	@Mock
	MimeMessage mockMessage; 
	
	@Test
	public void emailTest() {
		Mail mail=new Mail();
		mail.setContentType("abc");
		mail.setMailSubject("asasa");
		mail.setContentType("email");
		mail.setMailFrom("aab@gmail.com");
		mail.setMailContent("sdsdsd");
		mail.setMailTo("abc@gmail.com");
		when(javaMailSender.createMimeMessage()).thenReturn(mockMessage);
//		when(javaMailSender.send(any(MimeMessage.class)))
		service.sendEmail(mail);
	}

}
