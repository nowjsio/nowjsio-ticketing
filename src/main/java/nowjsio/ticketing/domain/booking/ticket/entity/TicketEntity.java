package nowjsio.ticketing.domain.booking.ticket.entity;

import jakarta.persistence.*;
import lombok.*;
import nowjsio.ticketing.domain.booking.pricing.entity.SectionPriceEntity;
import nowjsio.ticketing.domain.booking.reservation.entity.ReservationEntity;
import nowjsio.ticketing.domain.booking.schedule.entity.ScheduleEntity;
import nowjsio.ticketing.domain.booking.seat.entity.SeatEntity;
import nowjsio.ticketing.domain.common.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "schedule_id", nullable = false)
	private ScheduleEntity schedule;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seat_id", nullable = false)
	private SeatEntity seat;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "section_price_id", nullable = false)
	private SectionPriceEntity sectionPrice;

	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal price;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TicketStatus status;

	@OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ReservationEntity> reservations;
}