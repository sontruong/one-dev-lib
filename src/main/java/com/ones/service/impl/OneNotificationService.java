package com.ones.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.ones.type.Notification;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

/**
 * @author son.truong Apr 15, 2017 10:47:42 PM
 */
@Service
public class OneNotificationService {

	static final Logger LOGGER = LoggerFactory.getLogger(OneNotificationService.class);

	static final String TEST_EMAIL_ADDRESS_DOMAIN = "@weddyvn.com";

	@Value("${spring.mail.main.senderName}")
	String senderName;

	@Value("${spring.mail.main.username}")
	String senderMail;


	@Autowired
	FreeMarkerConfigurer freeMarkerConfigurer;

	@Autowired
	JavaMailSender mailSender;

	public String getTemplate(String templateName, Map<String, String> params) throws TemplateNotFoundException,
			MalformedTemplateNameException, ParseException, IOException, TemplateException {
		if (null == params) {
			params = new HashMap<>();
		}
		Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
		if (null == freeMarkerConfigurer.getConfiguration()) {
			return null;
		}
		return FreeMarkerTemplateUtils.processTemplateIntoString(template, params);
	}

	private MimeMessage getMailMessageFromNotification(Notification notification, boolean account) {
		// Get the default Session object.
		Session session = Session.getDefaultInstance(System.getProperties());

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(senderMail, senderName));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(notification.getTo()));

			// Set Subject: header field
			message.setSubject(notification.getSubject());

			// Send the actual HTML message, as big as you like
			message.setContent(notification.getMessage(), "text/html");

			return message;
		} catch (MessagingException mex) {
			mex.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Async
	public Future<MimeMessage> send(Notification notification, boolean account) {
		MimeMessage mimeMessage = getMailMessageFromNotification(notification, account);

		try {
			if (notification.getTo().toLowerCase().contains(TEST_EMAIL_ADDRESS_DOMAIN)) {
				LOGGER.debug("\n****** Email Semt ******:" + "\nto=" + notification.getTo() + "\nsubject="
						+ notification.getSubject() + "\nmessage=" + notification.getMessage());
			} else {
				mailSender.send(mimeMessage);
			}
		} catch (Exception e) {
			LOGGER.debug("Error sending notification: " + e.getMessage());
			mimeMessage = null;
		}

		return new AsyncResult<>(mimeMessage);
	}
}
