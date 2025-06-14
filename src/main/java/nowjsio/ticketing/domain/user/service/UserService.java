package nowjsio.ticketing.domain.user.service;

import nowjsio.ticketing.domain.user.dto.UserRequestDto;
import nowjsio.ticketing.domain.user.dto.UserResponseDto;

/**
 * The interface User service.
 */
public interface UserService {
	UserResponseDto register(UserRequestDto userRequestDto);
	boolean existsByUsername(String username);
}
