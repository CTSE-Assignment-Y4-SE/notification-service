package com.garage.notification_service.core.payload.request;

import com.garage.notification_service.core.type.EmailType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmailRequestDto {

	private String email;

	private String body;

	private EmailType emailType;

}
