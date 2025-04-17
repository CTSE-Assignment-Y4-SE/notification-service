package com.garage.notification_service.core.util;

import com.garage.notification_service.core.exception.ModuleException;
import com.garage.notification_service.core.type.EmailTemplateType;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
@UtilityClass
public class EmailBodyBuilder {

	public String buildEmailBody(EmailTemplateType emailTemplateType, Map<String, String> placeholders) {

		try {
			ClassPathResource resource = new ClassPathResource(emailTemplateType.getTemplatePath());
			StringBuilder template = new StringBuilder();

			try (BufferedReader reader = new BufferedReader(
					new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
				String line;
				while ((line = reader.readLine()) != null) {
					template.append(line).append(System.lineSeparator());
				}
			}

			String html = template.toString();
			for (Map.Entry<String, String> entry : placeholders.entrySet()) {
				html = html.replace("${" + entry.getKey() + "}", entry.getValue());
			}

			return html;

		}
		catch (Exception e) {
			log.error("EmailBodyBuilder.buildEmailBody(): Failed to build email body for email type {}: {}",
					emailTemplateType, e.getMessage(), e);
			throw new ModuleException("Error building email body");
		}
	}

}
