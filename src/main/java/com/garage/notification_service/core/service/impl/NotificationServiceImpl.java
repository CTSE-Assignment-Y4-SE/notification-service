package com.garage.notification_service.core.service.impl;

import com.garage.notification_service.core.constant.ApplicationMessages;
import com.garage.notification_service.core.exception.ModuleException;
import com.garage.notification_service.core.mapper.MapStructMapper;
import com.garage.notification_service.core.model.Notification;
import com.garage.notification_service.core.payload.common.ResponseEntityDto;
import com.garage.notification_service.core.payload.request.NotificationRequestDto;
import com.garage.notification_service.core.payload.response.NotificationResponseDto;
import com.garage.notification_service.core.payload.response.UserResponseDto;
import com.garage.notification_service.core.repository.NotificationRepository;
import com.garage.notification_service.core.service.NotificationService;
import com.garage.notification_service.core.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

	@NonNull
	private final UserService userService;

	@NonNull
	private final MapStructMapper mapStructMapper;

	@NonNull
	private NotificationRepository notificationRepository;

	@NonNull
	private MessageSource messageSource;

	@Override
	@Transactional
	public void saveNotification(NotificationRequestDto notificationRequestDto) {
		log.debug("NotificationServiceImpl.saveNotification: execution started");

		Notification notification = mapStructMapper.notificationRequestDtoToNotification(notificationRequestDto);
		notification.setViewed(false);

		notificationRepository.save(notification);

		log.debug("NotificationServiceImpl.saveNotification: execution ended");
	}

	@Override
	public ResponseEntityDto getAllNotifications() {
		log.debug("NotificationServiceImpl.getAllNotifications(): execution started");

		UserResponseDto currentUser = userService.getCurrentUser();

		List<Notification> notifications = notificationRepository.findAllByUserId(currentUser.getUserId());

		List<NotificationResponseDto> notificationResponseDtos = mapStructMapper
			.notificationListToNotificationResponseDtoList(notifications);

		log.debug("NotificationServiceImpl.getAllNotifications(): execution ended");
		return new ResponseEntityDto(true, notificationResponseDtos);
	}

	@Override
	@Transactional
	public ResponseEntityDto updateNotification(Long notificationId) {
		log.debug("NotificationServiceImpl.updateNotification: execution started");

		Optional<Notification> optionalNotification = notificationRepository.findById(notificationId);
		if (optionalNotification.isEmpty()) {
			throw new ModuleException(
					messageSource.getMessage(ApplicationMessages.ERROR_NOTIFICATION_NOT_FOUND, null, Locale.ENGLISH));
		}
		Notification notification = optionalNotification.get();

		notification.setViewed(true);

		notificationRepository.save(notification);

		log.debug("NotificationServiceImpl.updateNotification: execution started");
		return new ResponseEntityDto(true, null);
	}

}
