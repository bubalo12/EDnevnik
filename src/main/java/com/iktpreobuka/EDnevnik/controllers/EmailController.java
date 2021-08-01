package com.iktpreobuka.EDnevnik.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.EDnevnik.models.EmailObject;
import com.iktpreobuka.EDnevnik.services.EmailService;

@RestController
@RequestMapping("/email")
public class EmailController {

	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EmailService emailServices;

	private static String PATH_TO_ATTACHMENT = "H:\\BACKEND\\STS\\EDnevnik\\logs\\1.jpg";

	@RequestMapping(method = RequestMethod.POST, path = "/simpleEmail")
	public String setSimpleEmail(@RequestBody EmailObject emailObject) {
		if (emailObject == null || emailObject.getTo() == null || emailObject.getText() == null) {
			return null;
		}
		emailServices.sendSimpleMessage(emailObject);// emailServices.sendSimpleEmailMessage(emailObject);
		return "Email message successufully sent";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/templateEmail")
	public String sendTemplateMessage(@RequestBody EmailObject object) throws Exception {
		if (object == null || object.getTo() == null || object.getText() == null) {
			return null;
		}
		emailServices.sendTemplateMessage(object);
		return "Your mail has been sent!";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/emailWithAttachment")
	public String sendMessageWithAttachment(@RequestBody EmailObject object) throws Exception {
		if (object == null || object.getTo() == null || object.getText() == null) {
			return null;
		}
		emailServices.sendMessageWithAttachment(object, PATH_TO_ATTACHMENT);
		return "Your mail has been sent!";
	}

}
