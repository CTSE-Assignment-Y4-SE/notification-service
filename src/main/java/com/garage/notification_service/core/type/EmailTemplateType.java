package com.garage.notification_service.core.type;

import lombok.Getter;

@Getter
public enum EmailTemplateType {

	SERVICE_MANAGER_TEMP_PASSWORD("templates/service-manager-temp-password.html", "Temporary Password Notification"),
	USER_FORGOT_PASSWORD("templates/user-forgot-password.html", "Forgot Password"),;

	private final String templatePath;

	private final String subject;

	EmailTemplateType(String templatePath, String subject) {
		this.templatePath = templatePath;
		this.subject = subject;
	}

}
