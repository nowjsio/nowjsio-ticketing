package nowjsio.ticketing.domain.booking.payment.entity;


import jakarta.persistence.*;
import lombok.*;
import nowjsio.ticketing.domain.booking.reservation.entity.ReservationEntity;
import nowjsio.ticketing.domain.common.entity.BaseEntity;
import nowjsio.ticketing.domain.user.entity.UserEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PaymentEntity extends BaseEntity {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reservation_id", nullable = false)
	private ReservationEntity reservation;

	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal amount;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private PaymentStatus status;

	@Column(name = "paid_at", nullable = false)
	private LocalDateTime paidAt;
}