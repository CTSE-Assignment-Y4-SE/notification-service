package com.garage.notification_service.core.payload.response;

import com.garage.notification_service.core.type.NotificationType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class NotificationResponseDto {

	private Long notificationId;

	private String title;

	private String body;

	private NotificationType notificationType;

	private Long userId;

	private Long bookingId;

	private boolean isViewed;

	private LocalDateTime createdAt;

}
