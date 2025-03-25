package com.garage.notification_service.core.service;

import com.garage.notification_service.core.grpc.server.TokenValidateResponse;
import com.garage.notification_service.core.payload.response.UserResponseDto;
import lombok.NonNull;

public interface UserService {

	TokenValidateResponse isTokenValid(@NonNull String token);

	UserResponseDto getCurrentUser();

}
