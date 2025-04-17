package com.garage.notification_service.core.util;

import com.garage.notification_service.core.exception.ModuleException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@UtilityClass
public class DynamicFieldMapper {

	public static Map<String, String> mapFieldsToValues(Object dto) {
		Map<String, String> placeholders = new HashMap<>();
		try {
			Field[] fields = dto.getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				Object value = field.get(dto);
				if (value != null) {
					placeholders.put(field.getName(), value.toString());
				}
			}
		}
		catch (IllegalAccessException e) {
			log.error("DynamicFieldMapper.mapFieldsToValues(): Failed to map dynamic fields to values", e);
			throw new ModuleException("Failed to map fields to values");
		}
		return placeholders;
	}

}
