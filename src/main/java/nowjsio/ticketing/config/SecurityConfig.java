package nowjsio.ticketing.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nowjsio.ticketing.domain.user.repository.UserRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

	private final UserRepository userRepository;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return username -> userRepository.findByUsername(username)
			.map(user -> User.builder()
				.username(user.getUsername())
				.password(user.getPassword())
				.roles(user.getRole().name())
				.build())
			.orElseThrow(() -> {
				log.warn("Login attempt failed: user not found -> {}", username); // TODO: 로그인 시도 table 만들어서 N번 실패 차단 구현
				return new UsernameNotFoundException("User not found: " + username);
			});
	}

	// 1) actuator 용 체인 (Order 1)
	@Bean
	@Order(1)
	public SecurityFilterChain actuatorSecurity(HttpSecurity http) throws Exception {
		http
			.securityMatcher("/actuator/**")
			.authorizeHttpRequests(auth -> auth
				.anyRequest().hasRole("ADMIN")
			)
			.httpBasic(Customizer.withDefaults())
			.csrf(csrf -> csrf.disable());  // actuator POST는 CSRF 무시하거나 disable
		return http.build();
	}

	// 2) 웹(폼 로그인)용 체인 (Order 2)
	@Bean
	@Order(2)
	public SecurityFilterChain webSecurity(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(auth -> auth
				.requestMatchers("/", "/login", "/signup", "/css/**", "/js/**")
				.permitAll()
				.anyRequest().authenticated()
			)
			.formLogin(form -> form
				.loginPage("/login")
				.defaultSuccessUrl("/", true)
				.permitAll()
			)
			.logout(logout -> logout
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login?logout")
				.permitAll()
			);
		return http.build();
	}
}
