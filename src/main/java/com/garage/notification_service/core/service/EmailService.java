package com.garage.notification_service.core.service;

public interface EmailService {

	void sendEmail(String to, String subject, String body);

}
