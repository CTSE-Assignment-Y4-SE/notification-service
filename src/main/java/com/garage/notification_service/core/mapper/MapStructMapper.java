package com.garage.notification_service.core.mapper;

import com.garage.notification_service.core.model.Notification;
import com.garage.notification_service.core.payload.request.NotificationRequestDto;
import com.garage.notification_service.core.payload.response.NotificationResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

	List<NotificationResponseDto> notificationListToNotificationResponseDtoList(List<Notification> notifications);

	Notification notificationRequestDtoToNotification(NotificationRequestDto notificationRequestDto);

}
