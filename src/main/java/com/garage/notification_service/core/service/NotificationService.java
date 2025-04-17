package com.garage.notification_service.core.service;

import com.garage.notification_service.core.payload.common.ResponseEntityDto;
import com.garage.notification_service.core.payload.request.NotificationRequestDto;

public interface NotificationService {

	void saveNotification(NotificationRequestDto notificationRequestDto);

	ResponseEntityDto getAllNotifications();

	ResponseEntityDto updateNotification(Long notificationId);

}
