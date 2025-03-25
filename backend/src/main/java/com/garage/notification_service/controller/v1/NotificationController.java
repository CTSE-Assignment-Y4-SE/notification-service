package com.garage.notification_service.controller.v1;

import com.garage.notification_service.core.payload.common.ResponseEntityDto;
import com.garage.notification_service.core.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notification")
@AllArgsConstructor
public class NotificationController {

	@NonNull
	private final NotificationService notificationService;

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseEntityDto> getAllNotifications() {
		ResponseEntityDto response = notificationService.getAllNotifications();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PatchMapping(value = "/{notificationId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseEntityDto> updateNotification(@PathVariable Long notificationId) {
		ResponseEntityDto response = notificationService.updateNotification(notificationId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
