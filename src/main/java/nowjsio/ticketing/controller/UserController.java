package nowjsio.ticketing.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nowjsio.ticketing.domain.user.dto.UserRequestDto;
import nowjsio.ticketing.domain.user.service.UserService;

@RequiredArgsConstructor
@Controller
public class UserController {
	private final UserService userService;

	@GetMapping("/")
	public String home(Model model, Principal principal) {
		model.addAttribute("principal", principal);
		return "user/home";
	}

	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout,
		Model model) {
		if (error != null)
			model.addAttribute("loginError", "아이디 또는 비밀번호가 잘못되었습니다.");
		if (logout != null)
			model.addAttribute("logoutMsg", "정상적으로 로그아웃되었습니다.");
		return "user/login";
	}

	@GetMapping("/signup")
	public String signupForm(Model model) {
		model.addAttribute("userRequestDto", new UserRequestDto());
		return "user/signup";
	}

	@PostMapping("/signup")
	public String signup(
		@ModelAttribute("userRequestDto") @Validated UserRequestDto dto,
		BindingResult bindingResult
	) {
		if (bindingResult.hasErrors()) {
			return "user/signup";
		}
		userService.register(dto);
		return "redirect:/login?registered";
	}
}
