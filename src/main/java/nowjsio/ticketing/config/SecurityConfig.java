package nowjsio.ticketing.config;

import lombok.RequiredArgsConstructor;
import nowjsio.ticketing.domain.user.repository.UserRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
			.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http
			.csrf(csrf -> csrf.ignoringRequestMatchers(
				PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.POST, "/actuator/shutdown")))
			.authorizeHttpRequests(auth -> auth
				.requestMatchers("/actuator/**", "/v3/api-docs/**",
					"/swagger-ui.html",
					"/swagger-ui/**", "/admin/**")
				.hasRole("ADMIN")
				.requestMatchers("/", "/login", "/signup", "/css/**", "/js/**")
				.permitAll()
				.anyRequest()
				.authenticated())
			.httpBasic(Customizer.withDefaults())
			.formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/", true).permitAll())
			.logout(logout -> logout.logoutSuccessUrl("/").permitAll()
			);
		return http.build();
	}
}
