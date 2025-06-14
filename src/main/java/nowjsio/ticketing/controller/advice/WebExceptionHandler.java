package nowjsio.ticketing.controller.advice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.ui.Model;

import nowjsio.ticketing.domain.user.dto.UserRequestDto;
import nowjsio.ticketing.domain.user.exception.DuplicateUsernameException;

@ControllerAdvice(annotations = Controller.class)
public class WebExceptionHandler {

	@ExceptionHandler(DuplicateUsernameException.class)
	public String handleDup(DuplicateUsernameException ex,
		Model model) {
		model.addAttribute("userRequestDto", new UserRequestDto());
		model.addAttribute("signupError", ex.getMessage());
		return "user/signup";
	}


	// 그 외 모든 예외 → 500
	@ExceptionHandler(Exception.class)
	public String handleAll(Exception ex, Model model) {
		ex.printStackTrace();
		model.addAttribute("errorMessage", "서버 오류가 발생했습니다.");
		return "error/5xx";
	}
}
