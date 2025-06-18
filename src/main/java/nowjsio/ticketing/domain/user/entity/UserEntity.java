package nowjsio.ticketing.domain.user.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;
import nowjsio.ticketing.domain.booking.payment.entity.PaymentEntity;
import nowjsio.ticketing.domain.booking.reservation.entity.ReservationEntity;
import nowjsio.ticketing.domain.common.entity.BaseEntity;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private int point;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	//@Enumerated(EnumType.ORDINAL) -> 0 || 1  저장
	private UserRoleType role;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ReservationEntity> reservations;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PaymentEntity> payments;
}
