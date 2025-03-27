package com.garage.notification_service.core.service.impl;

import com.garage.notification_service.core.constant.ApplicationMessages;
import com.garage.notification_service.core.exception.ModuleException;
import com.garage.notification_service.core.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.util.Locale;

@Service
@AllArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

	@NonNull
	private final JavaMailSender mailSender;

	@NonNull
	private final MessageSource messageSource;

	@Override
	public void sendEmail(String to, String subject, String body) {
		log.debug("EmailServiceImpl.sendEmail(): execution started for recipient: {}", to);

		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body, true);

			mailSender.send(mimeMessage);

			log.debug("EmailServiceImpl.sendEmail(): Email sent successfully to {}", to);
		}
		catch (Exception e) {
			log.error("EmailServiceImpl.sendEmail(): Failed to send email to {}. Error: {}", to, e.getMessage(), e);
			throw new ModuleException(
					messageSource.getMessage(ApplicationMessages.ERROR_FAILED_TO_SEND_EMAIL, null, Locale.ENGLISH));
		}
		log.debug("EmailServiceImpl.sendEmail(): execution ended");
	}

}
