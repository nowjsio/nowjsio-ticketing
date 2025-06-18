package nowjsio.ticketing.domain.booking.pricing.entity;

import jakarta.persistence.*;
import lombok.*;
import nowjsio.ticketing.domain.booking.event.entity.EventEntity;
import nowjsio.ticketing.domain.booking.seat.entity.SeatEntity;
import nowjsio.ticketing.domain.common.entity.BaseEntity;

import java.math.BigDecimal;

@Entity
@Table(name = "section_prices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SectionPriceEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id", nullable = false)
	private EventEntity event;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seat_id", nullable = false)
	private SeatEntity seat;

	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal price;
}