package nowjsio.ticketing.domain.user.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import nowjsio.ticketing.domain.user.dto.UserRequestDto;
import nowjsio.ticketing.domain.user.dto.UserResponseDto;
import nowjsio.ticketing.domain.user.entity.UserEntity;
import nowjsio.ticketing.domain.user.entity.UserRoleType;
import nowjsio.ticketing.domain.user.exception.DuplicateUsernameException;
import nowjsio.ticketing.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public UserResponseDto register(UserRequestDto userRequestDto) {
		if (existsByUsername(userRequestDto.getUsername())) {
			throw new DuplicateUsernameException(String.format("Username [ %s ] already exists", userRequestDto.getUsername()));
		}
		UserEntity userEntity = userRepository.save(UserEntity.builder()
			.username(userRequestDto.getUsername())
			.password(passwordEncoder.encode(userRequestDto.getPassword()))
			.point(100_000)
			.role(UserRoleType.USER)
			.build());

		return UserResponseDto.builder().username(userEntity.getUsername()).build();
	}
	@Override
	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserResponseDto> findAllUsers() {
		return userRepository.findAll().stream()
			.map(p -> UserResponseDto.builder()
				.username(p.getUsername())
				.point(p.getPoint())
				.build())
			.toList();
	}
}
