package com.drones4hire.dronesapp.services.services.notifications;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

@Service
public class AWSEmailService extends AbstractEmailService
{
	@Autowired
	private AmazonSimpleEmailServiceClient client;

	@Value("#{environmentProperties['drones4hire.mail.sender']}")
	private String sender;
	
	@Override
	public String sendEmail(EmailType type, Map<String, Object> params, String... recipients)
	{
		Destination destination = new Destination().withToAddresses(recipients);
		Content subject = new Content(type.subject());
		Content emailBody = null;
		emailBody = buildBody(type.templatePath(), params);
		Message message = new Message(subject, new Body().withHtml(emailBody));
		SendEmailRequest request = new SendEmailRequest(sender, destination, message);
		return client.sendEmail(request).getMessageId();
	}

}
