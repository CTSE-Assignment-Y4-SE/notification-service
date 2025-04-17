package com.garage.notification_service.core.model;

import com.garage.notification_service.core.type.NotificationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
@Setter
@Getter
public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notification_id")
	private Long notificationId;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "body", nullable = false, columnDefinition = "TEXT")
	private String body;

	@Enumerated(EnumType.STRING)
	@Column(name = "notification_type", nullable = false)
	private NotificationType notificationType;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "booking_id")
	private Long bookingId;

	@Column(name = "is_viewed", nullable = false)
	private boolean isViewed = false;

	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
	}

}
