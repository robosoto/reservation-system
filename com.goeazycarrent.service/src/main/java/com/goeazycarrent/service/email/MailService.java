package com.goeazycarrent.service.email;

import org.springframework.stereotype.Service;

@Service
public interface MailService 
{
	public void sendEmail(Mail mail);
}
