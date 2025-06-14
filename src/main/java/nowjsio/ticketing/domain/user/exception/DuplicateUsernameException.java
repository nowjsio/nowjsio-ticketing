package nowjsio.ticketing.domain.user.exception;

public class DuplicateUsernameException extends RuntimeException {
	public DuplicateUsernameException(String message) {
		super(message);
	}
}
