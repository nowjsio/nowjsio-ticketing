package nowjsio.ticketing.domain.user.dto;

import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type User request dto.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@GroupSequence({ UserRequestDto.class, UserRequestDto.SizeChecks.class })
public class UserRequestDto {
	@NotBlank(message = "사용자명은 필수입니다.")
	private String username;

	@NotBlank(message = "비밀번호는 필수입니다.")
	@Size(min = 4, message = "비밀번호는 최소 4자 이상이어야 합니다.", groups = UserRequestDto.SizeChecks.class)
	private String password;

	public interface SizeChecks {}
}
