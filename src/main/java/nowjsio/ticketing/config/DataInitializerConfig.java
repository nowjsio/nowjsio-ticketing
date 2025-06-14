package nowjsio.ticketing.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;
import nowjsio.ticketing.domain.user.entity.UserEntity;
import nowjsio.ticketing.domain.user.entity.UserRoleType;
import nowjsio.ticketing.domain.user.repository.UserRepository;

@Configuration
@RequiredArgsConstructor
public class DataInitializerConfig {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Bean
	public CommandLineRunner init() {
		return args -> {
			// admin 계정
			if (userRepository.findByUsername("administrator").isEmpty()) {
				userRepository.save(UserEntity.builder()
					.username("administrator")
					.password(passwordEncoder.encode("qwe123Q!"))
					.point(100_000_000)
					.role(UserRoleType.ADMIN)
					.build());
			}
			// tester user1 계정
			if (userRepository.findByUsername("user1").isEmpty()) {
				userRepository.save(UserEntity.builder()
					.username("user1")
					.password(passwordEncoder.encode("123"))
					.point(100_000)
					.role(UserRoleType.USER)
					.build());
			}
			// tester user2 계정
			if (userRepository.findByUsername("user2").isEmpty()) {
				userRepository.save(UserEntity.builder()
					.username("user2")
					.password(passwordEncoder.encode("123"))
					.point(100_000)
					.role(UserRoleType.USER)
					.build());
			}
		};
	}
}
