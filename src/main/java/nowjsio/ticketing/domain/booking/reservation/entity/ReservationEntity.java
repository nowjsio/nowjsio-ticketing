package nowjsio.ticketing.domain.booking.reservation.entity;


import jakarta.persistence.*;
import lombok.*;
import nowjsio.ticketing.domain.booking.payment.entity.PaymentEntity;
import nowjsio.ticketing.domain.booking.ticket.entity.TicketEntity;
import nowjsio.ticketing.domain.common.entity.BaseEntity;
import nowjsio.ticketing.domain.user.entity.UserEntity;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "reservations")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ReservationEntity extends BaseEntity {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ticket_id", nullable = false)
	private TicketEntity ticket;

	@Column(name = "reserved_at", nullable = false)
	private LocalDateTime reservedAt;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private ReservationStatus status;

	@OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PaymentEntity> payments;
}