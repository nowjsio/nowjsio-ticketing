package nowjsio.ticketing.controller.advice;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import nowjsio.ticketing.domain.user.dto.UserRequestDto;
import nowjsio.ticketing.domain.user.exception.DuplicateUsernameException;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class ApiExceptionHandler {

	// 일단 현재는 전역으로 설정
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  // 500 상태로 설정
	public ErrorResponse handleAllExceptions(Exception ex, HttpServletRequest request) {
		log.error("Unhandled exception", ex);
		return ErrorResponse.builder()
			.timestamp(LocalDateTime.now())
			.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
			.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
			.message(ex.getMessage())
			.path(request.getRequestURI())
			.build();
	}
}
