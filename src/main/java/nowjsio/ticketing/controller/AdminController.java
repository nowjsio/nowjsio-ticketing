package nowjsio.ticketing.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nowjsio.ticketing.domain.user.dto.UserResponseDto;
import nowjsio.ticketing.domain.user.service.UserService;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

	private final UserService userService;

	@GetMapping("/findAll")
	public List<UserResponseDto> getAllUsers() {
		return userService.findAllUsers();
	}
}
