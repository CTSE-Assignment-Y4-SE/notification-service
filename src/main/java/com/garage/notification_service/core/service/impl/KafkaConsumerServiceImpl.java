package com.garage.notification_service.core.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.garage.notification_service.core.payload.request.NotificationRequestDto;
import com.garage.notification_service.core.payload.request.EmailRequestDto;
import com.garage.notification_service.core.service.EmailService;
import com.garage.notification_service.core.service.NotificationService;
import com.garage.notification_service.core.type.EmailTemplateType;
import com.garage.notification_service.core.type.EmailType;
import com.garage.notification_service.core.util.DynamicFieldMapper;
import com.garage.notification_service.core.util.EmailBodyBuilder;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class KafkaConsumerServiceImpl {

	@NonNull
	private ObjectMapper objectMapper;

	@NonNull
	private EmailService emailService;

	@NonNull
	private NotificationService notificationService;

	@KafkaListener(topics = "garage-notifications", groupId = "notification-service-group")
	public void consumeGarageMessage(String message) {
		try {
			NotificationRequestDto notificationRequestDto = objectMapper.readValue(message,
					NotificationRequestDto.class);
			notificationService.saveNotification(notificationRequestDto);
		}
		catch (Exception e) {
			log.error("KafkaConsumerServiceImpl.consumeGarageMessage: execution failed {}", message, e);
		}
	}

	@KafkaListener(topics = "user-notifications", groupId = "notification-service-group")
	public void consumeUserMessage(String message) {
		try {
			EmailRequestDto emailRequestDto = objectMapper.readValue(message, EmailRequestDto.class);

			Map<String, String> placeholders = DynamicFieldMapper.mapFieldsToValues(emailRequestDto);

			if (emailRequestDto.getEmailType().equals(EmailType.SERVICE_MANAGER_TEMP_PASSWORD)) {
				String emailBody = EmailBodyBuilder.buildEmailBody(EmailTemplateType.SERVICE_MANAGER_TEMP_PASSWORD,
						placeholders);
				emailService.sendEmail(emailRequestDto.getEmail(),
						EmailTemplateType.SERVICE_MANAGER_TEMP_PASSWORD.getSubject(), emailBody);
			}
			else if (emailRequestDto.getEmailType().equals(EmailType.USER_FORGOT_PASSWORD)) {
				String emailBody = EmailBodyBuilder.buildEmailBody(EmailTemplateType.USER_FORGOT_PASSWORD,
						placeholders);
				emailService.sendEmail(emailRequestDto.getEmail(), EmailTemplateType.USER_FORGOT_PASSWORD.getSubject(),
						emailBody);
			}

		}
		catch (JsonProcessingException e) {
			log.error("Failed to deserialize user message: {}", e.getMessage(), e);
		}
	}

}
